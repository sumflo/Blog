package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.model.Blog;
import homework.maven.springframework.blog.model.DTO.PostDTO;
import homework.maven.springframework.blog.model.Post;
import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.service.BlogService;
import homework.maven.springframework.blog.service.PostService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  private final PostService postService;
  private final BlogService blogService;

  public PostController(PostService postService, BlogService blogService) {
    this.postService = postService;
    this.blogService = blogService;
  }

  @GetMapping("/posts")
  public ResponseEntity<List<Post>> listPosts() {
    return new ResponseEntity<>(postService.listPosts(), HttpStatus.OK);
  }

  @PostMapping("/addPost")
  public ResponseEntity<String> addPost(@RequestBody PostDTO postDTO) {
    Blog currentBlog = blogService.findBlog(postDTO.getBlogId()).orElseThrow();
    postService.addPost(currentBlog, postDTO.getTitle(), postDTO.getText());
    return new ResponseEntity<>("Post is created.", HttpStatus.OK);
  }

  @DeleteMapping("/deletePost/{id}")
  public ResponseEntity<String> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ResponseEntity.ok("You have successfully deleted your post.");
  }

  @GetMapping("/findPost/{id}")
  public ResponseEntity<User> findPost(@PathVariable Long id) {
    return new ResponseEntity(postService.findPost(id), HttpStatus.OK);
  }

}
