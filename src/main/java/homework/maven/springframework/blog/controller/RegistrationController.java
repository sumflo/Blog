package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.model.registration.RegistrationRequest;
import homework.maven.springframework.blog.service.UserService;
import homework.maven.springframework.blog.service.registration.RegistrationService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
public class RegistrationController {

  private final UserService userService;
  private final RegistrationService registrationService;

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

  /* KÍSÉRLETEZÉS */
  @PostMapping(path = "/registration/experiment")
  public RedirectView register(@ModelAttribute ("register") RegistrationRequest request){
    return new RedirectView(registrationService.register(request));
  }

  @GetMapping(path = "/registration/experiment/confirm")
  public String confirm(@RequestParam("token") String token) {
    return registrationService.confirmToken(token);
  }

}
