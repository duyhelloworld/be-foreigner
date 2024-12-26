package vn.edu.huce.beforeigner.domains.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.remind.Remind;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Người dùng
 */
public class Account implements UserDetails {

    /**
     * Mã người dùng
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Tên tài khoản
     */
    @Column(length = 100, nullable = false, unique = true)
    private String username;

    /**
     * Họ tên
     */
    @Column(length = 200)
    private String fullname;

    /**
     * Email
     */
    @Column(length = 100, nullable = false)
    private String email;

    /**
     * Mật khẩu
     */
    @Column(length = 100)
    private String password;

    /**
     * Đã xác thực chưa
     */
    @Column(nullable = false)
    private boolean isVerified = false;

    /**
     * Ảnh đại diện
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String avatarUrl;

    /**
     * Mã ảnh đại diện
     */
    private String avatarPublicId;

    /**
     * Số chuỗi ngày học
     */
    @Column
    private Integer streakDays;

    /**
     * True nếu đã tăng streak, false nếu chưa
     */
    @Column
    private boolean isPlusStreak;

    /**
     * Vai trò
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Cấp độ người dùng
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserLevel level;

    /**
     * Số lượt truy cập còn lại 
     */
    private Integer quota;

    /**
     * Gói đăng kí hiện tại
     */
    @Enumerated(EnumType.STRING)
    private SubscriptionPlan plan;

    /**
     * Bên cung cấp token
     */
    @Enumerated(EnumType.STRING)
    private TokenProvider provider;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @OneToMany(mappedBy = "account")
    private Set<AccountToken> accountTokens = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<AccountSetting> accountSettings = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Remind> reminds = new HashSet<>();
}
