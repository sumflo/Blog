package homework.maven.springframework.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Blog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference(value = "user-blog")
  private User user;

  private String blogTitle;

  @OneToMany(mappedBy = "blog")
  @JsonManagedReference
  private List<Post> blogRegistries = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "template_id")
  private Template template;

  public Blog() {
  }

  public Blog(User user, String blogTitle, Template template) {
    this.user = user;
    this.blogTitle = blogTitle;
    this.template = template;
  }
}
