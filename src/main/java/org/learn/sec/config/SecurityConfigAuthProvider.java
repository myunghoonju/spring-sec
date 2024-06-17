//package org.learn.sec.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigAuthProvider {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.authenticationProvider(new CustomAuthProvider());
//        builder.authenticationProvider(new CustomAuthProvider2());
//
//        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).formLogin(Customizer.withDefaults());
////                same as line 22, 23
////                .authenticationProvider(new CustomAuthProvider())
////                .authenticationProvider(new CustomAuthProvider2());
//
//        return httpSecurity.build();
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
