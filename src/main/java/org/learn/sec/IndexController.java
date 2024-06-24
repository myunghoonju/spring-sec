package org.learn.sec;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/logoutsuccess")
    public String logoutsuccess() {
        return "logoutsuccess";
    }

    @GetMapping("/loginpage")
    public String loginpage() {
        return "loginpage";
    }

    @GetMapping("/anonymous")
    public String anonymous() {
        return "anonymous";
    }

    @GetMapping("/authenticate")
    public String authenticate(Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "anonymous";
        }

        return "authentication is null use @CurrentSecurityContext";
    }

    @GetMapping("/anonymousCtx")
    public String anonymousCtx(@CurrentSecurityContext SecurityContext ctx) {
        return ctx.getAuthentication().getName();
    }
}
