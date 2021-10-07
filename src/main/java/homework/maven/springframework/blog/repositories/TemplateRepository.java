package homework.maven.springframework.blog.repositories;

import homework.maven.springframework.blog.model.Template;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TemplateRepository extends CrudRepository<Template, Long> {

    Optional<Template> findByTemplateName(String templateName);

}
