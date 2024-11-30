package vn.edu.huce.beforeigner.domains.core;

import java.util.Collection;
import java.util.Collections;

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
import lombok.Setter;
import vn.edu.huce.beforeigner.constants.UserConstants;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 200)
    private String fullname;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String password;

    private String tempCode;

    @Column(nullable = false)
    private Integer streakDays = 0;

    private Boolean isFirstTry = true;

    private Boolean isVerified = false;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String avatarUrl;

    private String avatarPublicId;

    private String avatarFilename;

    @Enumerated(EnumType.STRING)
    private TokenProvider provider = TokenProvider.LOCAL;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserLevel level = UserLevel.BEGINNER;

    @Column(nullable = false)
    private Integer learnCountAvailaible = UserConstants.LEARN_COUNT_AVAILABLE;

    @Enumerated(EnumType.STRING)
    private SubscriptionPlan plan = SubscriptionPlan.FREE;
    
    private boolean isAllowMail = true;

    private boolean isAllowNotification = true;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}
