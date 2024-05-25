package org.learn.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// lambda only
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                    .formLogin(Customizer.withDefaults())
                    .rememberMe(re -> re.alwaysRemember(false)
                                        .tokenValiditySeconds(3600)
                                        .userDetailsService(userDetailsService())
                                        .rememberMeParameter("remember")
                                        .rememberMeCookieName("myCookie")
                                        .key("sec"));

        return httpSecurity.build();
    }

    // priority over yml setting security.user
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userB = User.withUsername("user")
                                .password("{noop}1234")
                                .roles("USER")
                                .build();

        UserDetails userC = User.withUsername("user2")
                                .password("{noop}1234")
                                .roles("USER")
                                .build();

        return new InMemoryUserDetailsManager(userB, userC);
    }
}
