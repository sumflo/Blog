package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.model.Blog;
import homework.maven.springframework.blog.model.DTO.BlogDTO;
import homework.maven.springframework.blog.model.Template;
import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.service.BlogService;
import homework.maven.springframework.blog.service.TemplateService;
import homework.maven.springframework.blog.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BlogController {

  private final BlogService blogService;
  private final UserService userService;
  private final TemplateService templateService;

  public BlogController(BlogService blogService, UserService userService,
      TemplateService templateService) {
    this.blogService = blogService;
    this.userService = userService;
    this.templateService = templateService;
  }

  @PostMapping("/addBlog")
  public ResponseEntity<String> addBlog(@RequestBody BlogDTO blogDTO) {
    User currentUser = userService.findUser(blogDTO.getUserId()).orElseThrow();
    Template currentTemplate = templateService.findTemplate(blogDTO.getTemplateId()).orElseThrow();
    blogService.addBlog(blogDTO.getBlogTitle(), currentUser, currentTemplate);
    return new ResponseEntity<>("Blog is created.", HttpStatus.OK);
  }

  @DeleteMapping("/deleteBlog/{id}")
  public ResponseEntity<String> deleteBolg(@PathVariable Long id) {
    blogService.deleteBlog(id);
    return ResponseEntity.ok("You have successfully deleted your Blog with id: " + id + ".");
  }

  @GetMapping("/findBlog/{id}")
  public ResponseEntity<Blog> findBlog(@PathVariable Long id) {
    return new ResponseEntity(blogService.findBlog(id), HttpStatus.OK);
  }

  @GetMapping("/findBlogByTitle/{title}")
  public ResponseEntity<Blog> findBlogByBlogTitle(@PathVariable String title) {
    return new ResponseEntity(blogService.findBlogByBlogTitle(title), HttpStatus.OK);
  }

  @GetMapping("/blogs")
  public ResponseEntity<List<Blog>> listBlogs() {
    return new ResponseEntity<>(blogService.listBlogs(), HttpStatus.OK);
  }

}
