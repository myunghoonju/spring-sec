package org.learn.sec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/loginpage")
    public String loginpage() {
        return "loginpage";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
