package vn.edu.huce.beforeigner.configurations;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.constants.UserConstants;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.infrastructures.leaderboardmodule.abstracts.ILeaderboardService;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts.IRemindService;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleConfig {

    private final IRemindService remindService;

    private final UserRepository userRepo;

    private final ILeaderboardService leaderboardService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void onNewDayJobs() {
        log.info("Start schedule onNewDayJobs at {}", LocalDateTime.now());
        int result = userRepo.resetShowedStreak(UserConstants.QUOTA_PER_DAY);
        log.info("Success reset first try of {} users", result);
        leaderboardService.updateUserRanks();
        log.info("Sucess update user ranks in LeaderBoard");
        log.info("Enc cronjob! See you at next day");
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void remind() {
        log.info("Start schedule remind at {}", LocalDateTime.now());
        remindService.testCronJob();
    }
}
