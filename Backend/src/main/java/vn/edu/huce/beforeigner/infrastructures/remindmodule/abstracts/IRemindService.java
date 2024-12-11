package vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.remind.RemindMethod;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.RemindDto;

public interface IRemindService {
    
    void remindLearnUser(User user, RemindMethod method, Integer lessonId);

    void remindWordByPushNotification(User user, Integer lessonId);

    void testCronJob();

    List<RemindDto> syncNotification(User user);
}
