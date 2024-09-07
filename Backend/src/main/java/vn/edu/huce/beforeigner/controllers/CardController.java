package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.ICardService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.CardDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.creatation.CreateCardDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail.CardDetailDto;

@AllArgsConstructor
@RestController
@RequestMapping("api/card")
public class CardController {
    
    private ICardService cardService;

    @GetMapping("/")
    public List<CardDto> getAll(
        @RequestParam(required = false) Integer categoryId) {
            return cardService.getAll(categoryId);
    }

    @GetMapping("{id}")
    public CardDetailDto getById(@PathVariable Integer id) {
        return cardService.getById(id);
    }

    @PostMapping("add")
    public void addNew(@RequestBody CreateCardDto createCardDto) {
        cardService.addNew(createCardDto);
    }
    
}
