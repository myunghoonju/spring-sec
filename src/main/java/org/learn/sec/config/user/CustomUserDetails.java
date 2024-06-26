package org.learn.sec.config.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final Account account;

    public CustomUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getAuthorities();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !account.expired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account.locked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !account.expiredPw();
    }

    @Override
    public boolean isEnabled() {
        return account.enabled();
    }
}
