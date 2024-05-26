package org.learn.sec.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// lambda only
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/logoutsuccess").permitAll()
                                                       .anyRequest().authenticated())
                    .formLogin(Customizer.withDefaults())
                    // logoutRequestMatcher takes priority over logoutUrl
                    .logout(out -> out.logoutUrl("logout")
                                      .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.POST.name()))
                                      .logoutSuccessUrl("/logoutsuccess")
                                      .logoutSuccessHandler(new LogoutSuccessHandler() {
                                          @Override
                                          public void onLogoutSuccess(HttpServletRequest request,
                                                                      HttpServletResponse response,
                                                                      Authentication authentication)
                                                  throws IOException, ServletException {
                                              response.sendRedirect("/logoutsuccess");
                                          }
                                      })
                                      .deleteCookies("JSESSIONID", "remember-me")
                                      .invalidateHttpSession(true)
                                      .clearAuthentication(true)
                                      .addLogoutHandler(new LogoutHandler() {
                                          @Override
                                          public void logout(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Authentication authentication) {
                                              HttpSession session = request.getSession();
                                              session.invalidate();
                                              SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(null);
                                              SecurityContextHolder.getContextHolderStrategy().clearContext();
                                          }
                                      }).permitAll());

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
