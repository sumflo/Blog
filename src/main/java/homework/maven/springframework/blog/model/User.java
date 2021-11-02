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
public class User implements UserDetails {

  @Id
  @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
  private Long id;

  /* Validálásnál a @NotBlank annotációval tudjuk garantálni, hogy ez az érték ne lehessen null "üres"
   * és a message attribútummal adhatunk meg hibaüzenetet.
   * Ezenkívül a Bean Validation a @NotBlank mellett sok más praktikus korlátozást is kínál.
   * Ez lehetővé teszi számunkra, hogy különböző érvényesítési szabályokat alkalmazzunk és kombináljunk
   * a korlátozott osztályokra.*/
  @Email(message = "Email should be valid.") //email validáció annotációval
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

  private Boolean locked = false;
  private Boolean enabled = false;

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


  /* generate -> equals() and hashCode() -> Template: IntelliJDefault -> id:Long marad csak bepipálva (az alapján szeretnénk az azonosságot) ->
   * (included is hashCode())bepipálva -> (non-null field)nem bepipálva, mer lehet 0 (vagy, ha nem szeretném nem) -> finish
   * helyettesíthető : @EqualsAndHashcode annotáció az osztály fölé */

  /*
   * Azért kell, hogy ha két objektumnak ugyanaz az ID-je, akkor a Hibernate ugyanannak az
   * objektumnak vegye őket (kapcsolja őket egymáshoz). ==>> elvileg szükség van rá az id megfeleő
   * működéséhez, én sem értem még nagyon.
   */

  /*
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    return id != null ? id.equals(user.id) : user.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
  */

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
    return Collections.singletonList(authority);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

}
