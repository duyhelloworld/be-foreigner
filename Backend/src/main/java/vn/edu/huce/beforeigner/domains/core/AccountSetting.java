package vn.edu.huce.beforeigner.domains.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.NoDeleteAuditedEntity;
import vn.edu.huce.beforeigner.domains.remind.NotificationMethod;
import vn.edu.huce.beforeigner.domains.remind.SettingType;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Cài đặt tài khoản
 */
public class AccountSetting extends NoDeleteAuditedEntity {

    /**
     * Mã setting
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Phương thức remind
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationMethod remindMethod;

    /**
     * Loại remind
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SettingType settingType;

    /**
     * Cờ cho phép
     */
    @Column(nullable = false)
    private boolean isEnabled;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Account account;
}
