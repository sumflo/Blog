package homework.maven.springframework.blog.service.registration;

import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.model.UserRole;
import homework.maven.springframework.blog.model.registration.RegistrationRequest;
import homework.maven.springframework.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/* A KÍSÉRLET RÉSZE*/
@Service
@AllArgsConstructor
public class RegistrationService {

  private final UserService userService;

  public String register(RegistrationRequest request){
    return userService.signUpUser( new User(
        request.getUserName(),
        request.getPassword(),
        UserRole.USER
    ));
  }

}
