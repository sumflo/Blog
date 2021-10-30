package homework.maven.springframework.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//ToDo: kitakarítani, a gettereket, settereket, átállni a lombokos annotációkra
@Entity
public class Template {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String templateName;

  public Template() {
  }

  public Template(String templateName) {
    this.templateName = templateName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Template template = (Template) o;

    return id != null ? id.equals(template.id) : template.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }
}
