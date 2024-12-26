package vn.edu.huce.beforeigner.infrastructures.remindmodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.domains.remind.NotificationMethod;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.infrastructures.remindmodule.dtos.RemindDto;

public interface IRemindService {
    
    void remindLearnUser(Account user, NotificationMethod method, Integer lessonId);

    void remindWordByPushNotification(Account user, Word word);
    
    List<RemindDto> syncNotification(Account user, NotificationMethod method);

    void testCronJob();

    void markRead(Account user, List<Integer> remindIds);
}
