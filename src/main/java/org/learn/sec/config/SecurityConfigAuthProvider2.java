//package org.learn.sec.config;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import java.io.IOException;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigAuthProvider2 {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.authenticationProvider(custom1());
//        builder.authenticationProvider(custom2());
//
//        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).formLogin(Customizer.withDefaults());
//
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public AuthenticationProvider custom1() {
//        return new CustomAuthProvider();
//    }
//
//    @Bean
//    public AuthenticationProvider custom2() {
//        return new CustomAuthProvider2();
//    }
//
//    // priority over yml setting security.user
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userB = User.withUsername("user")
//                                .password("{noop}1234")
//                                .roles("USER")
//                                .build();
//
//        UserDetails userC = User.withUsername("user2")
//                                .password("{noop}1234")
//                                .roles("USER")
//                                .build();
//
//        return new InMemoryUserDetailsManager(userB, userC);
//    }
//}
