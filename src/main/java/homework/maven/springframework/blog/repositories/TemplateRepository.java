package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Template;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<Template, Long> {

  Optional<Template> findByTemplateName(String templateName);

}
