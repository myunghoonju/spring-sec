package org.learn.sec;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginControllerWithoutFilter {

    private final AuthenticationManager authenticationManager;
    private final HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();

    public LoginControllerWithoutFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    public Authentication idx(Authentication authentication) {
        return authentication;
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // UsernamePasswordAuthenticationFilter.java's default url
    // disable filter and use below manual login
    @PostMapping("/login")
    public Authentication login(@RequestBody LoginRequest data, HttpServletRequest req, HttpServletResponse res) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(data.getId(), data.getPw());
        Authentication authenticated = authenticationManager.authenticate(token);

        SecurityContext ctx = SecurityContextHolder.getContextHolderStrategy().createEmptyContext();
        ctx.setAuthentication(authenticated);

        SecurityContextHolder.getContextHolderStrategy().setContext(ctx);

        repository.saveContext(ctx, req, res);

        return authenticated;
    }
}
