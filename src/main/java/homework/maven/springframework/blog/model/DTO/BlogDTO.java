package homework.maven.springframework.blog.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BlogDTO {

  /**
   * DTO - Data Transfer Object
   */

  private Long userId;
  private String blogTitle;
  private Long templateId;

  public BlogDTO(Long userId, String blogTitle, Long templateId) {
    this.userId = userId;
    this.blogTitle = blogTitle;
    this.templateId = templateId;
  }
}
