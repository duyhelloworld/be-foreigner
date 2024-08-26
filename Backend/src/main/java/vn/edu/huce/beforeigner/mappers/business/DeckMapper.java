package vn.edu.huce.beforeigner.mappers.business;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.Deck;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.DeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.DeckDetailDto;

@Component
@AllArgsConstructor
public class DeckMapper {
    
    public DeckDto toDto(Deck deck) {
        return DeckDto.builder()
            .id(deck.getId())
            .name(deck.getName())
            .build();
    }

    public DeckDetailDto toDetailDto(Deck deck) {
        return DeckDetailDto.builder()
            .id(deck.getId())
            .name(deck.getName())
            .description(deck.getDescription())
            .build();
    }

}
