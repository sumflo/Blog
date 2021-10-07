package homework.maven.springframework.blog.service;

import homework.maven.springframework.blog.model.Template;
import homework.maven.springframework.blog.repositories.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public void addTemplate (Template template){

        if(templateRepository.findByTemplateName(template.getTemplateName()).isPresent()){
            //TODO: Ex.Hand.
            System.out.println("This template is already created.");
        }

        templateRepository.save(new Template(template.getTemplateName()));
    }

    public Optional<Template>findTemplate (Long id){
        return templateRepository.findById(id);
    }
}