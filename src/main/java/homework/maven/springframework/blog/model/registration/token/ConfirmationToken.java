package homework.maven.springframework.blog.model.registration.token;

import homework.maven.springframework.blog.model.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  @SequenceGenerator(name = "confirmation_token_sequence", sequenceName = "confirmation_token_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
  private Long id;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime expiresAt;

  private LocalDateTime confirmedAt;

  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,
      LocalDateTime confirmedAt, User user) {
    this.token = token;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
    this.confirmedAt = confirmedAt;
    this.user = user;
  }
}
