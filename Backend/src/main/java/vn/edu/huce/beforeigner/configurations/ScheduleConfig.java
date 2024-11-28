package vn.edu.huce.beforeigner.configurations;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts.IRemindService;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.LearnRemindDto;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleConfig {

    private final IRemindService remindService;

    private final UserRepository userRepo;

    private final LessonRepository lessonRepo;

    @Scheduled(cron = "0 0 0 * * ?")
    public void onNewDayJobs() {
        log.info("Start schedule onNewDayJobs at {}", LocalDateTime.now());
        int result = userRepo.resetFirstTry();
        log.info("Success reset first try of {} users", result);
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void remind() {
        log.info("Start schedule remind at {}", LocalDateTime.now());
        userRepo.findUsersWantBeNotify()
                .stream().forEach(u -> {
                    LearnRemindDto learnRemindDto = new LearnRemindDto();
                    if (u.isAllowMail()) {
                        learnRemindDto.setLessonId(lessonRepo.findRandomLessonByUserLevel(u.getLevel()).map(l -> l.getId()).orElse(1));
                        remindService.remindByEmail(learnRemindDto, u);
                    }
                    if (u.isAllowNotification()) {
                        learnRemindDto.setLessonId(lessonRepo.findRandomLessonByUserLevel(u.getLevel()).map(l -> l.getId()).orElse(1));
                        remindService.remindByNotification(learnRemindDto, u);
                    }
                });
    }
}
