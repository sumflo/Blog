package homework.maven.springframework.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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

  //Todo: default értékadás - akkor kapjon értéket, amikor létrjön az adatbázisban
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Comment comment = (Comment) o;

    return id != null ? id.equals(comment.id) : comment.id == null;
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

  public User getUser() {
    return user;
  }

  public void setUser(User author) {
    this.user = author;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post reply) {
    this.post = reply;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDateTime getDATE_OF_REGISTRY() {
    return DATE_OF_REGISTRY;
  }
}
