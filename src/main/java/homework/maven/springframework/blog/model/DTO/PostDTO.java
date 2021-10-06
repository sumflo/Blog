package homework.maven.springframework.blog.model.DTO;

public class PostDTO {

    private Long blogId;
    private String title;
    private String text;

    public PostDTO(Long blogId, String title, String text) {
        this.blogId = blogId;
        this.title = title;
        this.text = text;
    }

    public Long getBlogId() {
        return blogId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
