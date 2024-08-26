package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.WordType;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.WordDetailDto;

@AllArgsConstructor
@RestController
@RequestMapping("api/word")
public class WordController {
    
    private IWordService wordService;

    @GetMapping
    public List<WordDto> getAll(
        @RequestParam(required = false, defaultValue = "") String keyword,
        @RequestParam(required = false) Integer categoryId,
        @RequestParam(required = false) String wordType
    ) {
        return wordService.getAll(keyword, categoryId,  WordType.caseSensitiveValue(wordType));
    }

    @GetMapping("{id}")
    public WordDetailDto getById(@PathVariable Integer id) {
        return wordService.getById(id);
    }

    @PostMapping("add")
    public void addNew(@RequestBody CreateWordDto createWordDto) {
        wordService.addNew(createWordDto);
    }
    
}
