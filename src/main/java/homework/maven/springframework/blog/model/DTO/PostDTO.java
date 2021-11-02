package homework.maven.springframework.blog.model.DTO;

import lombok.Getter;

@Getter
public class PostDTO {

  private Long blogId;
  private String title;
  private String text;

  public PostDTO(Long blogId, String title, String text) {
    this.blogId = blogId;
    this.title = title;
    this.text = text;
  }
}
