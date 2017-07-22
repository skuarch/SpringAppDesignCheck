package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class IndexController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @GetMapping("{id}")
    public Person getPerson(@PathVariable("id") Long id){
        return personRepository.findOne(id);
    }
    
    
}
