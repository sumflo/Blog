package homework.maven.springframework.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JsonBackReference(value = "user-comment")
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id")
  @JsonBackReference
  private Post post;

  private String text;

  private LocalDateTime DATE_OF_REGISTRY;

  public Comment() {
    DATE_OF_REGISTRY = LocalDateTime.now();
  }

  public Comment(User user, Post post, String text) {
    this.user = user;
    this.post = post;
    this.text = text;
    this.DATE_OF_REGISTRY = LocalDateTime.now();
  }

}
