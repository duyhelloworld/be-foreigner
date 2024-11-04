package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/question")
public class QuestionController {
    
    // private IQuestionService questionService;

    // @IsAdmin
    // @GetMapping("/")
    // public PagingResult<ExamineQuestionDto> getAll(@RequestParam PagingRequest pagingRequest) {
    //     // return questionService.getAll(pagingRequest);
    // }

    // @IsAdmin
    // @GetMapping("{id}")
    // public ExamineQuestionDto getById(@PathVariable Integer id) {
    //     return questionService.getById(id);
    // }

    // @IsAdmin
    // @PostMapping
    // public ExamineQuestionDto addNew(@RequestBody CreateQuestionDto createQuestionDto) {
    //     return questionService.addNew(createQuestionDto);
    // }

    // @IsAdmin
    // @DeleteMapping("{id}")
    // public void delete(@PathVariable Integer id) {
    //     questionService.delete(id);
    // }
}
