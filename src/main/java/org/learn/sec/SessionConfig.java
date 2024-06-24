package org.learn.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SessionConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/invalid", "/expired").permitAll()
                                               .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .sessionManagement(s -> s.invalidSessionUrl("/invalid")
                                         .maximumSessions(1)
                                         .maxSessionsPreventsLogin(false)
                                         .expiredUrl("/expired"));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(User.withUsername("user").password("{noop}1234").roles("USER").build());
    }
}
