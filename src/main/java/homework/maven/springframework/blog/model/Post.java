package homework.maven.springframework.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "blog_id")
  @JsonBackReference
  private Blog blog;

  private String title;
  private String text;

  @DateTimeFormat(pattern = "yyyy-MM-dd") //ToDO: szépre készíteni
  private final LocalDateTime DATE_OF_REGISTRY;

  @OneToMany(mappedBy = "post")
  @JsonManagedReference
  private List<Comment> commentList;

  public Post() {
    DATE_OF_REGISTRY = LocalDateTime.now();
  }

  public Post(Blog blog, String title, String text) {
    this.blog = blog;
    this.title = title;
    this.text = text;
    this.DATE_OF_REGISTRY = LocalDateTime.now();
    this.commentList = new ArrayList<>();
  }

}
