package homework.maven.springframework.blog.util;

import homework.maven.springframework.blog.model.*;
import homework.maven.springframework.blog.repositories.*;
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
    public DataLoader(BlogRepository blogRepository, CommentRepository commentRepository, PostRepository postRepository,
                      TemplateRepository templateRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.templateRepository = templateRepository;
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments arguments){

       userRepository.save(new User("Jucus", "pass".toCharArray()));
       userRepository.save(new User("Béla", "word".toCharArray()));

       templateRepository.save(new Template("RoseGold"));

       blogRepository.save(new Blog(userRepository.findByUserName("Jucus").orElseThrow(), "Hogyan ne bukjunk meg Java-ból?",
               templateRepository.findByTemplateName("RoseGold").orElseThrow()));

       postRepository.save(new Post(blogRepository.findBlogByBlogTitle("Hogyan ne bukjunk meg Java-ból?").orElseThrow(),
               "Spring", "Bár azt hiszed a tavasz köszöntött be, súlyos tévedés..."));

       commentRepository.save(new Comment(userRepository.findByUserName("Béla").orElseThrow(),
               postRepository.findByTitle("Spring").orElseThrow(), "because Winter is coming..."));

    }
}
