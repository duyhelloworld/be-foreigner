package vn.edu.huce.beforeigner.entities.core;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(columnList = "id, username, email"))
public class User {

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

    @Enumerated(EnumType.STRING)
    private TokenProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public User(String username, String fullname, String email, String password, TokenProvider provider, Role role) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.provider = provider;
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return this.username.equals(user.username);
    }

}
