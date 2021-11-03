package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Template;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {

  Optional<Template> findByTemplateName(String templateName);

}
