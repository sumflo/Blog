package homework.maven.springframework.blog.repositories.registration.token;

import homework.maven.springframework.blog.model.registration.token.ConfirmationToken;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {

  Optional<ConfirmationToken> findByToken(String token);
}
