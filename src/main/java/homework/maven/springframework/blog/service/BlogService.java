package homework.maven.springframework.blog.service;

import homework.maven.springframework.blog.model.Blog;
import homework.maven.springframework.blog.model.Template;
import homework.maven.springframework.blog.model.User;
import homework.maven.springframework.blog.repositories.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void addBlog(String blogTitle, User user, Template template){
        blogRepository.save(new Blog(user, blogTitle, template));
    }

    public void deleteBlog(Long id){
        if(blogRepository.findById(id).isPresent()){
            blogRepository.deleteById(id);
        }else{
            System.out.println("This blogId does not exist.");
        }
    }

    //findBlogById
    public Optional<Blog> findBlog(Long id){
        return blogRepository.findById(id);
    }

    //findBlogByTitle
    public Optional<Blog> findBlogByBlogTitle(String title){
        return blogRepository.findBlogByBlogTitle(title);
    }

    //listBlogs
    public List<Blog> listBlogs(){
        return blogRepository.findAll();
    }

}
