package org.learn.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfigWithContext {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        AuthenticationManager manager = builder.build();

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/login").permitAll()
                                               .anyRequest().authenticated())

            .formLogin(Customizer.withDefaults())
            //.securityContext(ctx -> ctx.requireExplicitSave(false))
            .authenticationManager(manager)
            .addFilterBefore(customAuthenticationFilter(http, manager),
                             UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public CustomAuthenticationFilter customAuthenticationFilter(HttpSecurity http, AuthenticationManager manager) throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(http);
        filter.setAuthenticationManager(manager);

        return filter;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(User.withUsername("user")
                                                  .password("{noop}1234")
                                                  .roles("USER")
                                                  .build());
    }
}
