package homework.maven.springframework.blog.service.registration.token;

import homework.maven.springframework.blog.model.registration.token.ConfirmationToken;
import homework.maven.springframework.blog.repositories.registration.token.ConfirmationTokenRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/* KÍSÉRLETEZÉS */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

  private final ConfirmationTokenRepository confirmationTokenRepository;

  public void saveConfirmationToken(ConfirmationToken token){
    confirmationTokenRepository.save(token);
  }

  public Optional<ConfirmationToken> getToken(String token) {
    return confirmationTokenRepository.findByToken(token);
  }

  public void setConfirmedAt(String token) {
    confirmationTokenRepository.findByToken(token)
        .orElseThrow(() -> new IllegalStateException("Token not found."))
        .setConfirmedAt(LocalDateTime.now());
  }

}
