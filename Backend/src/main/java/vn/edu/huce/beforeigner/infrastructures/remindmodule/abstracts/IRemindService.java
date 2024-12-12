package vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.remind.RemindMethod;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.RemindDto;

public interface IRemindService {
    
    void remindLearnUser(User user, RemindMethod method, Integer lessonId);

    void remindWordByPushNotification(User user, Word word);
    
    List<RemindDto> syncNotification(User user, RemindMethod method);

    void testCronJob();

    void markRead(User user, List<Integer> remindIds);
}
