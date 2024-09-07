package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.IDeckService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.DeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.creatation.CreateDeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail.DeckDetailDto;

@AllArgsConstructor
@RestController
@RequestMapping("api/deck")
public class DeckController {
    
    private IDeckService deckService;

    @GetMapping("/")
    public List<DeckDto> getAll() {
        return deckService.getAll();
    }

    @GetMapping("{id}")
    public DeckDetailDto getById(@PathVariable Integer id) {
        return deckService.getById(id);
    }

    @PostMapping("add")
    public void addNew(@RequestBody CreateDeckDto createDeckDto) {
        deckService.addNew(createDeckDto);
    }
    
}
