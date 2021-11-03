package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findAll();

  Optional<Post> findByTitle(String title);

}
