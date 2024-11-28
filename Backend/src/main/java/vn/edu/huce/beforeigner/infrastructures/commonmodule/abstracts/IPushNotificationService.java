package vn.edu.huce.beforeigner.infrastructures.commonmodule.abstracts;

import java.util.Map;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface IPushNotificationService {

    void send(String recipientToken, String title, String body, Map<String, String> data) throws FirebaseMessagingException ;
}
