package homework.maven.springframework.blog.util;

import homework.maven.springframework.blog.model.Blog;
import homework.maven.springframework.blog.model.Comment;
import homework.maven.springframework.blog.model.Post;
import homework.maven.springframework.blog.model.Template;
import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.model.UserRole;
import homework.maven.springframework.blog.repositories.BlogRepository;
import homework.maven.springframework.blog.repositories.CommentRepository;
import homework.maven.springframework.blog.repositories.PostRepository;
import homework.maven.springframework.blog.repositories.TemplateRepository;
import homework.maven.springframework.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

  private BlogRepository blogRepository;
  private CommentRepository commentRepository;
  private PostRepository postRepository;
  private TemplateRepository templateRepository;
  private UserRepository userRepository;

  @Autowired
  public DataLoader(BlogRepository blogRepository, CommentRepository commentRepository,
      PostRepository postRepository,
      TemplateRepository templateRepository, UserRepository userRepository) {
    this.blogRepository = blogRepository;
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
    this.templateRepository = templateRepository;
    this.userRepository = userRepository;
  }

  public void run(ApplicationArguments arguments) {

    userRepository.save(new User("jucus@gmail.com", "pass", UserRole.USER));
    userRepository.save(new User("bela@gmail.com", "word", UserRole.USER));

    templateRepository.save(new Template("RoseGold"));

    blogRepository.save(new Blog(userRepository.findByUsername("jucus@gmail.com").orElseThrow(),
        "Hogyan ne bukjunk meg Java-ból?",
        templateRepository.findByTemplateName("RoseGold").orElseThrow()));

    postRepository.save(new Post(
        blogRepository.findBlogByBlogTitle("Hogyan ne bukjunk meg Java-ból?").orElseThrow(),
        "Spring", "Bár azt hiszed a tavasz köszöntött be, súlyos tévedés..."));

    commentRepository.save(new Comment(userRepository.findByUsername("bela@gmail.com").orElseThrow(),
        postRepository.findByTitle("Spring").orElseThrow(), "because Winter is coming..."));

  }
}
