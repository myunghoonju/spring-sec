package org.learn.sec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/invalid")
    public String invalid() {
        return "Hello Invalidated World";
    }

    @GetMapping("/expired")
    public String expired() {
        return "Hello Expired World";
    }
}
