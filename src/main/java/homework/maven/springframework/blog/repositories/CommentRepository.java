package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Comment;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    @Transactional
    void deleteById(Long id);
}
