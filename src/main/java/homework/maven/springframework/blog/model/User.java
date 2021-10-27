package homework.maven.springframework.blog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /** Validálásnál a @NotBlank annotációval tudjuk garantálni, hogy ez az érték ne lehessen null "üres"
   * és a message attribútummal adhatunk meg hibaüzenetet.
   * Ezenkívül a Bean Validation a @NotBlank mellett sok más praktikus korlátozást is kínál.
   * Ez lehetővé teszi számunkra, hogy különböző érvényesítési szabályokat alkalmazzunk és kombináljunk
   * a korlátozott osztályokra.*/
  @NotBlank(message = "Name is mandatory.")
  private String username;

  @NotBlank(message = "Password is mandatory.")
  private String password;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference(value = "user-blog")
  private List<Blog> blogs;

  @OneToMany
  @JoinColumn(name = "user_id")
  @JsonManagedReference(value = "user-comment")
  private List<Comment> commentList;

  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /** generate -> equals() and hashCode() -> Template: IntelliJDefault -> id:Long marad csak bepipálva (az alapján szeretnénk az azonosságot) ->
   * (included is hashCode())bepipálva -> (non-null field)nem bepipálva, mer lehet 0 (vagy, ha nem szeretném nem) -> finish */

  /**
   * Azért kell, hogy ha két objektumnak ugyanaz az ID-je, akkor a Hibernate ugyanannak az
   * objektumnak vegye őket (kapcsolja őket egymáshoz). ==>> elvileg szükség van rá az id megfeleő
   * működéséhez, én sem értem még nagyon.
   */
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String userName) {
    this.username = userName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Blog> getBlogs() {
    return blogs;
  }

  public void setBlogs(List<Blog> blogs) {
    this.blogs = blogs;
  }

  public List<Comment> getCommentList() {
    return commentList;
  }

  public void setCommentList(List<Comment> commentList) {
    this.commentList = commentList;
  }
}
