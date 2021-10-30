package homework.maven.springframework.blog.model.registration.token;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {

  @Id
  @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
  private Long id;

  @Column(nullable = false)
  private String token;
  private LocalDateTime createdAt;
  private LocalDateTime expiredAt;
  private LocalDateTime confirmedAt;

  public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,
      LocalDateTime confirmedAt) {
    this.token = token;
    this.createdAt = createdAt;
    this.expiredAt = expiredAt;
    this.confirmedAt = confirmedAt;
  }
}
