package vn.edu.huce.beforeigner.infrastructures.commonmodule.impls;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts.IPushNotificationService;

@Service
@RequiredArgsConstructor
public class PushNotificationService implements IPushNotificationService {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public void send(String recipientToken, String title, String body, Map<String, String> data)
            throws FirebaseMessagingException {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
        Message msg = Message.builder()
                .setToken(recipientToken)
                .setNotification(notification)
                .putAllData(data)
                .build();
        firebaseMessaging.send(msg);
    }

}
