package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.repositories.WordRepo;

@AllArgsConstructor
@RestController
@RequestMapping("api/word")
public class WordController {
    
    private WordRepo wordRepo;

    @GetMapping
    public String hello() {
        wordRepo.count();
        return "Hello";
    }
}
