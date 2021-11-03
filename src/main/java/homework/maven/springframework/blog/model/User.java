package homework.maven.springframework.blog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class User{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Email(message = "Email should be valid.")
  @NotBlank(message = "Name is mandatory.")
  private String username; // -->> email

  /* - a későbbi átalakításokhoz
  private String firstName;
  private String lastName;
  private String authorName;
  */

  @NotBlank(message = "Password is mandatory.")
  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  private boolean active;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference(value = "user-blog")
  private List<Blog> blogs;

  @OneToMany
  @JoinColumn(name = "user_id")
  @JsonManagedReference(value = "user-comment")
  private List<Comment> commentList;

  public User() {
  }

  public User(String username, String password, UserRole userRole) {
    this.username = username;
    this.password = password;
    this.userRole = userRole;
  }

}
