package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Blog;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Long> {

  Optional<Blog> findBlogByBlogTitle(String title);

  List<Blog> findAll();

  @Transactional
  void deleteById(Long id);
}
