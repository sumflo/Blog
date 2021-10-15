package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String renderRegistrationPage(){
        return "registration";
    }

}
