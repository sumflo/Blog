package homework.maven.springframework.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
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

}
