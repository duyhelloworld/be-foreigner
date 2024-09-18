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
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.notificationmodule.abstracts.INotificationService;
import vn.edu.huce.beforeigner.infrastructures.notificationmodule.dtos.LearnRemindDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    @Value("${application.notification.app-icon}")
    private String appIcon;

    private final FirebaseMessaging firebaseMessaging;

    private final AppObjectMapper objectMapper;

    @Override
    public void remind(LearnRemindDto learnReminderDto, User targetUser) {
        Notification notification = Notification.builder()
                .setTitle(learnReminderDto.getTitle())
                .setBody(learnReminderDto.getMessage())
                .setImage(appIcon)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(targetUser.getFirebaseToken())
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            log.error("Error when sending message: \n- Message : {}\n- Error : {}",
                    objectMapper.toJson(message),
                    e.getMessage());
        }
    }
}
