package vn.edu.huce.beforeigner.infrastructures.notificationmodule.impls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.UserToken;
import vn.edu.huce.beforeigner.domains.core.repo.UserTokenRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.notificationmodule.abstracts.INotificationService;
import vn.edu.huce.beforeigner.infrastructures.notificationmodule.dtos.LearnRemindDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    @Value("${application.notification.app-icon}")
    private String appIcon;

    private final FirebaseMessaging firebaseMessaging;

    private final UserTokenRepository userTokenRepo;

    private final AppObjectMapper objectMapper;

    @Override
    public void remind(LearnRemindDto learnReminderDto, User targetUser) {
        Notification notification = Notification.builder()
                .setTitle(learnReminderDto.getTitle())
                .setBody(learnReminderDto.getMessage())
                .setImage(appIcon)
                .build();

        UserToken userToken = userTokenRepo.findByLastModifiedByAndType(targetUser.getUsername(), TokenType.NOTIFICATION)
            .orElseThrow(() -> new AppException(ResponseCode.NOTIFICATION_TOKEN_NOT_FOUND));
        Message message = Message.builder()
                .setNotification(notification)
                .setToken(userToken.getToken())
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            log.error("Error when sending message: \n- Message : {}\n- Error : {}",
                    objectMapper.toJson(message),
                    e.getMessage());
        }
    }

    @Override
    public void test(String token) {
        try {
            firebaseMessaging.send(Message.builder()
            .setToken(token)
            .setNotification(Notification.builder()
                .setTitle("Be Foreigner")
                .setBody("Hê nhô xin chào bạn nhớ")
                .setImage("https://res.cloudinary.com/dqzwh7zvo/image/upload/v1726656428/be-foreigner-icon.png")
                .build())
            .build());
        } catch (FirebaseMessagingException e) {
            log.error("Error when sending message: {}",
                    e.getMessage());
        }
    }
}
