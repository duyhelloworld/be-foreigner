package vn.edu.huce.beforeigner.domains.core;

import java.util.Collection;
import java.util.Collections;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.common.UserLevel;
import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(columnList = "id, username, email", unique = true))
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

    @ManyToOne
    private CloudFile avatar;

    @Enumerated(EnumType.STRING)
    private TokenProvider provider = TokenProvider.LOCAL;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserLevel level = UserLevel.BEGINNER;

    @Column(insertable = false)
    private Integer experiences = 0;

    @Enumerated(EnumType.STRING)
    private SubscriptionPlan plan = SubscriptionPlan.FREE;
    
    @Column(insertable = false)
    private Integer diamonds = 0;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    public User(String username, String fullname, String email, String password) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }
}
