package homework.maven.springframework.blog.repositories;

public interface EmailSender {

  void send(String to, String email);

}
