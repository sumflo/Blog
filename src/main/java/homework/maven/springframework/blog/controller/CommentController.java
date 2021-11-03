package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.model.Comment;
import homework.maven.springframework.blog.model.DTO.CommentDTO;
import homework.maven.springframework.blog.model.Post;
import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.service.CommentService;
import homework.maven.springframework.blog.service.PostService;
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
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CommentController {

  private final CommentService commentService;
  private final PostService postService;
  private final UserService userService;

  public CommentController(CommentService commentService, PostService postService,
      UserService userService) {
    this.commentService = commentService;
    this.postService = postService;
    this.userService = userService;
  }

  @GetMapping("/comments")
  public ResponseEntity<List<Comment>> listComments() {
    return new ResponseEntity<>(commentService.listComments(), HttpStatus.OK);
  }

  @PostMapping("/addComment")
  public ResponseEntity<String> addComment(@RequestBody CommentDTO commentDTO) {
    Post currentPost = postService.findPost(commentDTO.getPostId()).orElseThrow();
    User currentUser = userService.findUser(commentDTO.getUserId()).orElseThrow();
    commentService.addComment(currentUser, currentPost, commentDTO.getText());
    return new ResponseEntity<>("Comment is created.", HttpStatus.OK);
  }

  @DeleteMapping("/deleteComment/{id}")
  public ResponseEntity<String> deleteComment(@PathVariable Long id) {
    commentService.deleteComment(id);
    return ResponseEntity.ok("You have successfully deleted your comment.");
  }

  @GetMapping("/findComment/{id}")
  public ResponseEntity<String> findComment(@PathVariable Long id) {
    return new ResponseEntity(commentService.findComment(id), HttpStatus.OK);
  }

}
