package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Comment;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAll();

  @Transactional
  void deleteById(Long id);
}
