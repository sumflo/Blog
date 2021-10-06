package homework.maven.springframework.blog.model.DTO;

public class BlogDTO {

    /** DTO - Data Transfer Object*/

    private Long userId;
    private String blogTitle;
    private String templateName;

    public BlogDTO(Long userId, String blogTitle, String templateName) {
        this.userId = userId;
        this.blogTitle = blogTitle;
        this.templateName = templateName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public String getTemplateName() {
        return templateName;
    }
}
