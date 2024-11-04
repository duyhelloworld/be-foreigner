package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsUser;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;


@AllArgsConstructor
@RestController
@RequestMapping("api/word")
public class WordController {
    
    private IWordService wordService;

    @IsUser
    @GetMapping("today")
    public WordDto getTodayWord(
        @AuthenticationPrincipal User user
    ) {
        return wordService.getTodayWord(user);
    }
    // @IsAdmin
    // @GetMapping("/")
    // public PagingResult<WordDto> getAll(
    //     @RequestParam(required = false) Integer topicId,
    //     @RequestParam(required = false) PagingRequest pagingRequest
    //     ) {
    //         return wordService.getAll(pagingRequest, topicId);
    // }

    // @IsAdmin
    // @GetMapping("{id}")
    // public WordDetailDto getById(@PathVariable Integer id) {
    //     return wordService.getById(id);
    // }

    // @IsAdmin
    // @PostMapping("/")
    // public void addNew(@RequestBody CreateWordDto createWordDto) {
    //     wordService.addNew(createWordDto);
    // }

    // @IsAdmin
    // @PutMapping("{id}")
    // public WordDetailDto update(@PathVariable Integer id,
    // @RequestBody UpdateWordDto updateWordDto) {
    //     return wordService.update(id, updateWordDto);
    // }

    // @IsAdmin
    // @DeleteMapping("{id}")
    // public void delete(@PathVariable Integer id) {
    //     wordService.delete(id);
    // }
}
