package vn.edu.huce.beforeigner.infrastructures.remindmodule.impls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.core.repo.UserTokenRepository;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.domains.remind.Remind;
import vn.edu.huce.beforeigner.domains.remind.RemindMethod;
import vn.edu.huce.beforeigner.domains.remind.RemindType;
import vn.edu.huce.beforeigner.domains.remind.repo.RemindRepository;
import vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts.IEmailService;
import vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts.IPushNotificationService;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts.IRemindService;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.RemindDto;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.mappers.RemindMapper;
import vn.edu.huce.beforeigner.utils.NumberUtils;
import vn.edu.huce.beforeigner.utils.RemindTemplateUtils;

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

    private final RemindMapper remindMapper;

    @Override
    public void remindLearnUser(User user, RemindMethod method, Integer lessonId) {
        Map<String, String> data = new HashMap<>();
        data.put("lessonId", lessonId.toString());
        try {
            var remind = getRemind(user, method, RemindType.LEARN_REMIND, objectMapper.writeValueAsString(data));
            switch (method) {
                case EMAIL:
                    try {
                        emailService.send(user.getEmail(), adminMail, remind.getTitle(), remind.getBody());
                        log.info("Send push notification success");
                    } catch (MessagingException e) {
                        log.error("Error when sending message: \n- Message : {}",
                                e.getMessage());
                    } 
                    break;
                case NOTIFICATION:
                    var userToken = userTokenRepo
                        .findByLastModifiedByAndType(AuditorConfig.getAuditor(user), TokenType.NOTIFICATION);
                    if (userToken.isEmpty()) {
                        return;
                    }
                    try {
                        pushNotificationService.send(userToken.get().getToken(), remind.getTitle(), remind.getBody(),
                                data);
                        remindRepo.save(remind);
                        log.info("Send push notification success");
                    } catch (FirebaseMessagingException e) {
                        log.error("Error when sending push notification: \n- Message : {}",
                                e.getMessage());
                    } 
                    break;
            }
            remindRepo.save(remind);
        } catch (JsonProcessingException ex) {

        }
    }

    @Override
    public void testCronJob() {
        log.info("Start cronjob : {}", LocalTime.now());
        var users = userRepo.findUsersWantBeNotify();
        int totalEmail = 0, totalNoti = 0;
        for (User user : users) {
            Page<Lesson> pageLesson = lessonRepo.findAll(
                    Pageable.ofSize(1).withPage(NumberUtils.randomNumber(0, (int) lessonRepo.count())));
            if (pageLesson.isEmpty()) {
                log.info("Error when find lesson correspond {}", user.getUsername());
                continue;
            }
            int lessonId = pageLesson.getContent().get(0).getId();
            if (user.isAllowMail()) {
                log.info("Sending mail to user {}: {}", user.getUsername(), user.getEmail());
                remindLearnUser(user, RemindMethod.EMAIL, lessonId);
                totalEmail++;
            }
            if (user.isAllowNotification()) {
                log.info("Sending push notification to {}", user.getUsername());
                remindLearnUser(user, RemindMethod.NOTIFICATION, lessonId);
                totalNoti++;
            }
        }
        log.info("OK! Remind {} users by email and {} users by notification!", totalEmail, totalNoti);   
    }

    @Override
    public void remindWordByPushNotification(User user, Integer lessonId) {
        
    }

    private Remind getRemind(User user, RemindMethod method, RemindType type, String data) {
        Remind remind = new Remind();
        var template = RemindTemplateUtils.getTemplate(method, user);
        remind.setBody(template.getBody());
        remind.setMethod(method);
        remind.setTitle(template.getTitle());
        remind.setRecipient(user);
        remind.setBody(data);
        return remind;
    }

    @Override
    public List<RemindDto> syncNotification(User user) {
        return remindRepo.findByRecipient(user)
            .stream()
            .map(r -> remindMapper.toDto(r))
            .toList();
    }

}
