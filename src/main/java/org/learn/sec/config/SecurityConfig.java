package org.learn.sec.config;

import org.learn.sec.config.handler.LoginFailure;
import org.learn.sec.config.handler.LoginSuccess;
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
//                    .formLogin(Customizer.withDefaults());
//                                     form.loginPage("/loginpage")
                    .formLogin(form -> form.loginProcessingUrl("/loginproc")
                                           // ignored coz of handlers
//                                           .defaultSuccessUrl("/", false)
//                                           .failureUrl("/failed")
                                           .usernameParameter("id")
                                           .passwordParameter("pw")
                                           .successHandler(new LoginSuccess())
                                           .failureHandler(new LoginFailure())
                                           .permitAll()

                    );

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
