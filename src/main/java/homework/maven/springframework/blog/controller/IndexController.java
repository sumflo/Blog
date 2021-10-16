package homework.maven.springframework.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/index")
  public String renderHomePage() {
    return "index";
  }


}
