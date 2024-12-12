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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardUser;
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

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 6)
    private String tempCode;

    @Enumerated(EnumType.STRING)
    private CodeTarget codeTarget;
    
    @Column(nullable = false)
    private boolean isVerified = false;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String avatarUrl;

    private String avatarPublicId;

    private String avatarFilename;

    @Column(nullable = false)
    private Integer streakDays = 0;

    /**
     * True nếu đã show, false nếu chưa
     */
    private boolean isShowedStreak;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserLevel level;

    @Column(nullable = false)
    private Integer quota;

    @Enumerated(EnumType.STRING)
    private SubscriptionPlan plan;

    private boolean isAllowMail;

    private boolean isAllowWordNotification;

    private boolean isAllowNotification;

    @Enumerated(EnumType.STRING)
    private TokenProvider provider = TokenProvider.LOCAL;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<LeaderBoardUser> leaderBoardUsers = new HashSet<>(); 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}
