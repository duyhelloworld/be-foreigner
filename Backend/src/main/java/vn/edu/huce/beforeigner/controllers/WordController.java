package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsAdmin;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

import org.springframework.web.bind.annotation.PutMapping;


@AllArgsConstructor
@RestController
@RequestMapping("api/word")
public class WordController {
    
    private IWordService wordService;

    @IsAdmin
    @GetMapping("/")
    public PagingResult<WordDto> getAll(
        @RequestParam(required = false) Integer topicId,
        @RequestParam(required = false) PagingRequest pagingRequest
        ) {
            return wordService.getAll(pagingRequest, topicId);
    }

    @IsAdmin
    @GetMapping("{id}")
    public WordDetailDto getById(@PathVariable Integer id) {
        return wordService.getById(id);
    }

    @IsAdmin
    @PostMapping("/")
    public void addNew(@RequestBody CreateWordDto createWordDto) {
        wordService.addNew(createWordDto);
    }

    @IsAdmin
    @PutMapping("{id}")
    public WordDetailDto update(@PathVariable Integer id,
    @RequestBody UpdateWordDto updateWordDto) {
        return wordService.update(id, updateWordDto);
    }

    @IsAdmin
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        wordService.delete(id);
    }
}
