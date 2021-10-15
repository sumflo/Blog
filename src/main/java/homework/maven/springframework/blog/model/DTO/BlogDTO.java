package homework.maven.springframework.blog.model.DTO;

public class BlogDTO {

  /**
   * DTO - Data Transfer Object
   */

  private Long userId;
  private String blogTitle;
  private Long templateId;

  public BlogDTO(Long userId, String blogTitle, String templateName) {
    this.userId = userId;
    this.blogTitle = blogTitle;
    this.templateId = templateId;
  }

  public Long getUserId() {
    return userId;
  }

  public String getBlogTitle() {
    return blogTitle;
  }

  public Long getTemplateId() {
    return templateId;
  }
}
