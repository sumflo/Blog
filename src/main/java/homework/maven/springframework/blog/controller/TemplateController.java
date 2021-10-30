package homework.maven.springframework.blog.controller;

import homework.maven.springframework.blog.model.Template;
import homework.maven.springframework.blog.service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

  private final TemplateService templateService;

  public TemplateController(TemplateService templateService) {
    this.templateService = templateService;
  }

  @PostMapping("/addTemplate")
  public ResponseEntity<String> addTemplate(@RequestBody Template template) {
    templateService.addTemplate(template);
    return new ResponseEntity<>("Template is created.", HttpStatus.OK);
  }
}
