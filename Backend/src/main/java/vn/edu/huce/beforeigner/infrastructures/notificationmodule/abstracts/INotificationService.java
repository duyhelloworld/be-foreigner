package vn.edu.huce.beforeigner.infrastructures.notificationmodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.notificationmodule.dtos.LearnRemindDto;

public interface INotificationService {
    
    void remind(LearnRemindDto learnReminderDto, User targetUser);

    void test(String token);
    
}
