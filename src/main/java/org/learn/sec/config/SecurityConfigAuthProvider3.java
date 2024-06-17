//package org.learn.sec.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
//public class SecurityConfigAuthProvider3 {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManagerBuilder builder, AuthenticationConfiguration configuration) throws Exception {
//        AuthenticationManagerBuilder managerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//        managerBuilder.authenticationProvider(custom1());
//
//        ProviderManager authenticationManager = (ProviderManager) configuration.getAuthenticationManager();
//        authenticationManager.getProviders().remove(0);
//        builder.authenticationProvider(new DaoAuthenticationProvider());
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
