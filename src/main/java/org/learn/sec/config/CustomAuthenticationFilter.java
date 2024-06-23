package org.learn.sec.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationFilter(HttpSecurity httpSecurity) {
        super(new AntPathRequestMatcher("/api/login", "GET"));
        setSecurityContextRepository(repo(httpSecurity));
    }

    private SecurityContextRepository repo(HttpSecurity httpSecurity) {
        SecurityContextRepository repo = httpSecurity.getSharedObject(SecurityContextRepository.class);
        if (repo == null) {
            repo = new DelegatingSecurityContextRepository(new HttpSessionSecurityContextRepository(), new RequestAttributeSecurityContextRepository());
        }

        return repo;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException,
                                                                                     IOException,
                                                                                     ServletException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, pw);

        return getAuthenticationManager().authenticate(token);
    }
}
