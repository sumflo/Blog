package homework.maven.springframework.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @GetMapping("/index")
  public String renderHomePage() {
    return "index";
  }


}
