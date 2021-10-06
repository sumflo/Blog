package homework.maven.springframework.blog.model.DTO;

public class CommentDTO {

    private Long userId;
    private Long postId;
    private String text;

    public CommentDTO(Long userId, Long postId, String text) {
        this.userId = userId;
        this.postId = postId;
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }

}
