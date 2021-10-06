package homework.maven.springframework.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-blog")
    private User user;

    private String blogTitle;

    @OneToMany(mappedBy = "blog")
    @JsonManagedReference
    private List<Post> blogRegistries = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "template_templateName")
    private Template template;

    public Blog() {
    }

    public Blog(User user, String blogTitle, Template template) {
        this.user = user;
        this.blogTitle = blogTitle;
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog blog = (Blog) o;

        return id != null ? id.equals(blog.id) : blog.id == null;
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

    public User getUser() {
        return user;
    }

    public void setUser(User author) {
        this.user = author;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public List<Post> getBlogRegistries() {
        return blogRegistries;
    }

    public void setBlogRegistries(List<Post> blogRegistries) {
        this.blogRegistries = blogRegistries;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
