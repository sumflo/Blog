package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.User;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAll();

  Optional<User> findByUsername(String userName);

  Optional<User> deleteUsersById(Long id);

}
