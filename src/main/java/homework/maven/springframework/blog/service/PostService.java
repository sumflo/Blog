package homework.maven.springframework.blog.service;

import homework.maven.springframework.blog.model.Blog;
import homework.maven.springframework.blog.model.Post;
import homework.maven.springframework.blog.repositories.PostRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public void addPost(Blog blog, String title, String text) {
    postRepository.save(new Post(blog, title, text));
  }

  public void deletePost(Long id) {
    if (postRepository.findById(id).isPresent()) {
      postRepository.deleteById(id);
    } else {
      System.out.println("This post does not exist.");
    }
  }

  public List<Post> listPosts() {
    return postRepository.findAll();
  }

  public Optional<Post> findPost(Long id) {
    return postRepository.findById(id);
  }
}
