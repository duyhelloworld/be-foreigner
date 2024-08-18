package vn.edu.huce.beforeigner.dtos.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vn.edu.huce.beforeigner.entities.core.User;

@Getter
@AllArgsConstructor
public class AuthenticatedUser implements UserDetails {

    private User user;

    public static AuthenticatedUser instance(User user) {
        return new AuthenticatedUser(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}