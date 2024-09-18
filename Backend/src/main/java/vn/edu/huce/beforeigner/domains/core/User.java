package vn.edu.huce.beforeigner.domains.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.exam.Bookmark;
import vn.edu.huce.beforeigner.domains.statistic.UserLessonStatistic;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(columnList = "id, username, email"))
public class User implements UserDetails {

    @Id
    private String id = UUID.randomUUID().toString();
    
    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 200)
    private String fullname;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String avatar;

    @Column(length = 255)
    private String firebaseToken;

    @Enumerated(EnumType.STRING)
    private TokenProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private UserLevel level = UserLevel.BEGINNER;

    @OneToMany(mappedBy = "user")
    private Set<Bookmark> userQuestionBookmarks = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserLessonStatistic> userLessonStatistics = new HashSet<>();

    public User(String username, String fullname, String email, String password, TokenProvider provider, Role role) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.provider = provider;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}
