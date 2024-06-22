package org.learn.sec.config.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private String username;

    private String password;

    private Collection<GrantedAuthority> authorities;

    public boolean expired() {
        return false;
    }

    public boolean locked() {
        return false;
    }

    public boolean expiredPw() {
        return false;
    }

    public boolean enabled() {
        return true;
    }
}
