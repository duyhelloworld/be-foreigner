package vn.edu.huce.beforeigner.services.impls.core;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.dtos.auth.AuthenticatedUser;
import vn.edu.huce.beforeigner.repositories.UserRepo;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return AuthenticatedUser
                .instance(userRepo.findByUsername(username).orElse(null));
    }

}
