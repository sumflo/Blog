package homework.maven.springframework.blog.model.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/* A KÍSÉRLET RÉSZE*/
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationRequest {

  private final String userName;
  private final String password;

  /*
  private final String firstName;
  private final String lastName;
  private final String email;
   */

}
