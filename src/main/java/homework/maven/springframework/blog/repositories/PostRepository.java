package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAll();

    Optional<Post> findByTitle(String title);

}
