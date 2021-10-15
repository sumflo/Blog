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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Post post = (Post) o;

    return id != null ? id.equals(post.id) : post.id == null;
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

  public Blog getBlog() {
    return blog;
  }

  public void setBlog(Blog blog) {
    this.blog = blog;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public List<Comment> getCommentList() {
    return commentList;
  }

  public void setCommentList(List<Comment> commentList) {
    this.commentList = commentList;
  }
}
