package homework.maven.springframework.blog.model.DTO;

import lombok.Getter;

@Getter
public class CommentDTO {

  private Long userId;
  private Long postId;
  private String text;

  public CommentDTO(Long userId, Long postId, String text) {
    this.userId = userId;
    this.postId = postId;
    this.text = text;
  }
}
