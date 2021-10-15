package homework.maven.springframework.blog.service;

import homework.maven.springframework.blog.model.Comment;
import homework.maven.springframework.blog.model.Post;
import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.repositories.CommentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public void addComment(User user, Post post, String text) {
    commentRepository.save(new Comment(user, post, text));
  }

  public void deleteComment(Long id) {
    if (commentRepository.findById(id).isPresent()) {
      commentRepository.deleteById(id);
    } else {
      System.out.println("This comment does not exist.");
    }
  }

  public Optional<Comment> findComment(Long id) {
    return commentRepository.findById(id);
  }

  public List<Comment> listComments() {
    return commentRepository.findAll();
  }

}
