package vn.edu.huce.beforeigner.infrastructures.coremodule.impls;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.AuthenticatedUser;
import vn.edu.huce.beforeigner.repositories.UserRepository;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return AuthenticatedUser
                .instance(userRepo.findByUsername(username).orElse(null));
    }

}
