package vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.LearnRemindDto;

public interface IRemindService {
    
    void remindByNotification(LearnRemindDto learnReminderDto, User targetUser);

    void remindByEmail(LearnRemindDto learnReminderDto, User targetUser);

    void testCronJob();
    
}
