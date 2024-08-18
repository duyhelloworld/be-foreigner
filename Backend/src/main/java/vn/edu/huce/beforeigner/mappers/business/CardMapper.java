package vn.edu.huce.beforeigner.mappers.business;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.dtos.bussiness.CardDto;
import vn.edu.huce.beforeigner.dtos.bussiness.detail.CardDetailDto;
import vn.edu.huce.beforeigner.entities.learn.Card;

@Component
@AllArgsConstructor
public class CardMapper {

    private WordMapper wordMapper;

    private DeckMapper deckMapper;

    public CardDto toDto(Card card) {
        return CardDto.builder()
                .id(card.getId())
                .imageUrl(card.getImageUrl())
                .word(wordMapper.toDto(card.getWord()))
                .build();
    }

    public CardDetailDto toDetailDto(Card card) {
        return CardDetailDto.builder()
                .id(card.getId())
                .imageUrl(card.getImageUrl())
                .word(wordMapper.toDto(card.getWord()))
                .deck(deckMapper.toDto(card.getDeck()))
                .build();
    }
}
