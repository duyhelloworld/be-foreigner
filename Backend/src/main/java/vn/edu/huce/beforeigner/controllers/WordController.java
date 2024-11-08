package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsAdmin;
import vn.edu.huce.beforeigner.annotations.IsUser;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;


@RestController
@RequestMapping("api/word")
@RequiredArgsConstructor
public class WordController {
    
    private final IWordService wordService;

    @IsUser
    @GetMapping("today")
    public WordDto getTodayWord(@AuthenticationPrincipal User user) {
        return wordService.getTodayWord(user);
    }
    
    @IsAdmin
    @GetMapping("/all")
    public PagingResult<WordDto> getAll(
        @RequestParam(required = false) PagingRequest pagingRequest) {
            return wordService.getAll(pagingRequest);
    }

    @IsAdmin
    @GetMapping("{id}")
    public WordDetailDto getDetailById(@PathVariable Integer id) {
        return wordService.getDetailById(id);
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
