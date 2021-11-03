package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String renderLoginPage() {
    return "login";
  }

/*  @PostMapping("/login")
  public String authenticateUser(@ModelAttribute User user){
    if (userService.findByUserName(user.getUserName()).isPresent()){
      return "redirect:/home";

    }

  }*/

}
