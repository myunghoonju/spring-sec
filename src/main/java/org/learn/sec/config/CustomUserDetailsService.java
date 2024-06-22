package org.learn.sec.config;

import org.learn.sec.config.user.Account;
import org.learn.sec.config.user.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"userA".equals(username)) {
            return null;
        }

        return new CustomUserDetails(new Account("userA" ,
                                                 "{noop}1234",
                                                 List.of(new SimpleGrantedAuthority("USER"))));
    }
}
