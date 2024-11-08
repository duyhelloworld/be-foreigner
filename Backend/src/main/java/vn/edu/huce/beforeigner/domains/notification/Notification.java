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
     * Mã thông báo. Dùng luôn trường messageId khi gửi notification bằng firebaseMessaging
     */
    private String id;

    /**
     * Tiêu đề
     */
    private String title;

    /**
     * Nội dung
     */
    private String content;

    private Integer lessonId;
}
