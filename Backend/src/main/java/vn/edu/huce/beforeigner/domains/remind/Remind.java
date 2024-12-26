package vn.edu.huce.beforeigner.domains.remind;

import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.OnlyDateAuditedEntity;
import vn.edu.huce.beforeigner.domains.core.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Setter
@Entity
public class Remind extends OnlyDateAuditedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Tiêu đề
     */
    @Column(nullable = false, length = 200)
    private String title;

    /**
     * Nội dung
     */
    @Column(nullable = false)
    private String body;

    /**
     * Dữ liệu dạng json
     */
    private String data;

    @Column(nullable = false)
    private boolean isRead = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationMethod method;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;
}
