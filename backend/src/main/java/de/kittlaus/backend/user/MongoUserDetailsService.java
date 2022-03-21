package de.kittlaus.backend.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    private final MyUserService userService;

    public MongoUserDetailsService(MyUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .map(myUser -> new User(myUser.getUsername(), myUser.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + myUser.getRole()))))
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
