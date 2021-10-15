package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Comment;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

  List<Comment> findAll();

  @Transactional
  void deleteById(Long id);
}
