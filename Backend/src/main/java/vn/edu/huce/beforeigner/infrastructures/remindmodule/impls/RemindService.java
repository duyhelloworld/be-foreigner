package vn.edu.huce.beforeigner.infrastructures.remindmodule.impls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.configurations.AuditorConfig;
import vn.edu.huce.beforeigner.domains.core.TokenType;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.UserToken;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.core.repo.UserTokenRepository;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.domains.remind.Remind;
import vn.edu.huce.beforeigner.domains.remind.RemindType;
import vn.edu.huce.beforeigner.domains.remind.repo.RemindRepository;
import vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts.IEmailService;
import vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts.IPushNotificationService;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts.IRemindService;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.LearnRemindDto;
import vn.edu.huce.beforeigner.utils.NotificationUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemindService implements IRemindService {

    @Value("${application.notification.app-icon}")
    private String appIcon;

    @Value("${application.mail.admin-mail}")
    private String adminMail;

    private final UserRepository userRepo;
    
    private final LessonRepository lessonRepo;

    private final UserTokenRepository userTokenRepo;

    private final RemindRepository remindRepo;

    private final IEmailService emailService;

    private final IPushNotificationService pushNotificationService;

    private final AppObjectMapper objectMapper;

    @Override
    public void remindByNotification(LearnRemindDto learnReminderDto, User targetUser) {

        UserToken userToken = userTokenRepo
                .findByLastModifiedByAndType(AuditorConfig.getAuditor(targetUser), TokenType.NOTIFICATION)
                .orElse(null);
        if (userToken == null) {
            return;
        }     
        Remind remind = new Remind();
        remind.setType(RemindType.NOTIFICATION);
        remind.setBody(NotificationUtil.getRemindBody());
        remind.setTitle(NotificationUtil.getRemindTitle(targetUser.getFullname()));
        Map<String, String> data = new HashMap<>();
        data.put("lessonId", learnReminderDto.getLessonId().toString());
        try {
            pushNotificationService.send(userToken.getToken(), remind.getTitle(), remind.getBody(),
            data);
            remind.setData(objectMapper.writeValueAsString(data));
            remindRepo.save(remind);
            log.info("Send push notification success");
        } catch (FirebaseMessagingException e) {
            log.error("Error when sending message: \n- Message : {}",
                    e.getMessage());
        } catch (JsonProcessingException e) {
            log.error("Error when save remind: Unable to cast data to string");
        }
    }

    @Override
    public void remindByEmail(LearnRemindDto learnReminderDto, User targetUser) {
        Remind remind = new Remind();
        remind.setType(RemindType.EMAIL);
        remind.setTitle(NotificationUtil.getRemindTitle(targetUser.getFullname()));
        remind.setBody(NotificationUtil.getRemindBody());
        Map<String, String> data = new HashMap<>();
        data.put("lessonId", learnReminderDto.getLessonId().toString());
        try {
            emailService.send(targetUser.getEmail(), adminMail, remind.getTitle(), remind.getBody());
            remind.setData(objectMapper.writeValueAsString(data));
            remindRepo.save(remind);
            log.info("Send push notification success");
        } catch (MessagingException e) {
            log.error("Error when sending message: \n- Message : {}",
                    e.getMessage());
        } catch (JsonProcessingException e) {
            log.error("Error when save remind: Unable to cast data to string");
        }
    }

    @Override
    public void testCronJob() {
        userRepo.findUsersWantBeNotify()
                .stream().forEach(u -> {
                    LearnRemindDto learnRemindDto = new LearnRemindDto();
                    if (u.isAllowMail()) {
                        learnRemindDto.setLessonId(lessonRepo.findRandomLessonByUserLevel(u.getLevel()).map(l -> l.getId()).orElse(1));
                        remindByEmail(learnRemindDto, u);
                    }
                    if (u.isAllowNotification()) {
                        learnRemindDto.setLessonId(lessonRepo.findRandomLessonByUserLevel(u.getLevel()).map(l -> l.getId()).orElse(1));
                        remindByNotification(learnRemindDto, u);
                    }
                });
    }
    
}
