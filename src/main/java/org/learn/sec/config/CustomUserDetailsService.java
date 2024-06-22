package org.learn.sec.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"userA".equals(username)) {
            return null;
        }

        return User.withUsername("userA")
                                .password("{noop}1234")
                                .roles("USER")
                                .build();
    }
}
