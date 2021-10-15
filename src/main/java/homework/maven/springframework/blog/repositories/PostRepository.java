package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

  List<Post> findAll();

  Optional<Post> findByTitle(String title);

}
