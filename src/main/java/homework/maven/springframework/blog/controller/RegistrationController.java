package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.service.UserService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/registration")
  public String renderRegistrationPage(Model model) {
    model.addAttribute("user", new User());
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(@Valid User user, BindingResult bindingResult, Model model){

    if(bindingResult.hasErrors()){
      model.addAttribute("registrationForm", user);
      return "registration";
    }try{
      userService.addUser(user);
    }catch(Exception e){
      bindingResult.rejectValue("username", "user.username", "An account already exists for this username");
      model.addAttribute("registrationForm", user);
      return "registration";
    }
    return "redirect:/login";
  }

  



}
