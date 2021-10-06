package homework.maven.springframework.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Template {

    @Id
    private String templateName;

    public Template() {
    }

    public Template(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Template template = (Template) o;

        return templateName != null ? templateName.equals(template.templateName) : template.templateName == null;
    }

    @Override
    public int hashCode() {
        return templateName != null ? templateName.hashCode() : 0;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
