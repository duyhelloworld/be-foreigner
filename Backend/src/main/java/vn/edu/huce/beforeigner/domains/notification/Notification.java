package vn.edu.huce.beforeigner.domains.notification;

import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Setter
@Entity
public class Notification {
    
    @Id
    /**
     * Mã thông báo. Hiện tại là messageId khi gửi noti bằng firebaseMessaging
     */
    private String id;

    private String title;

}
