package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts.IQuestionService;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.QuestionDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.creation.CreateQuestionDto;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingRequest;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("api/question")
public class QuestionController {
    
    private IQuestionService questionService;

    @GetMapping("/")
    public PagingResult<QuestionDto> getAll(@RequestParam PagingRequest pagingRequest) {
        return questionService.getAll(pagingRequest);
    }

    @GetMapping("{id}")
    public QuestionDto getById(@PathVariable Integer id) {
        return questionService.getById(id);
    }

    @PostMapping("")
    public QuestionDto addNew(@RequestBody CreateQuestionDto createQuestionDto) {
        return questionService.addNew(createQuestionDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        questionService.delete(id);
    }
}
