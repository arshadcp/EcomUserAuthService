package dev.Arshad.EcomUserAuthService.Security;

import dev.Arshad.EcomUserAuthService.Entity.User;
import dev.Arshad.EcomUserAuthService.Exception.UserNotFoundException;
import dev.Arshad.EcomUserAuthService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomSpringUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findUserByEmail(username).orElseThrow(
                ()-> new UserNotFoundException("User with given username not found")
        );
        return new CustomSpringUserDetails(user);
    }
}
