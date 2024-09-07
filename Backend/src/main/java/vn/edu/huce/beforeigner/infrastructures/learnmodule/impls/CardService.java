package vn.edu.huce.beforeigner.infrastructures.learnmodule.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.Card;
import vn.edu.huce.beforeigner.entities.learn.Deck;
import vn.edu.huce.beforeigner.entities.learn.Word;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.ICardService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.CardDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.creatation.CreateCardDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail.CardDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.updatation.UpdateCardDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.mappers.CardMapper;
import vn.edu.huce.beforeigner.repositories.CardRepository;
import vn.edu.huce.beforeigner.repositories.DeckRepository;
import vn.edu.huce.beforeigner.repositories.WordRepository;

@Service
@AllArgsConstructor
public class CardService implements ICardService {

    private CardRepository cardRepo;

    private DeckRepository deckRepo;

    private WordRepository wordRepo;

    private CardMapper cardMapper;

    @Override
    public List<CardDto> getAll(Integer categoryId) {
        return cardRepo.findAll().stream().map(c -> cardMapper.toDto(c)).toList();
    }

    @Override
    public CardDetailDto getById(Integer id) {
        return cardMapper.toDetailDto(cardRepo.findById(id).orElse(null));
    }

    @Override
    public void addNew(CreateCardDto createCardDto) {
        Deck deck = deckRepo.findById(createCardDto.getDeckId())
                .orElseThrow(() -> new AppException(ResponseCode.DECK_NOT_FOUND));
        Word word = wordRepo.findById(createCardDto.getWordId())
                .orElseThrow(() -> new AppException(ResponseCode.WORD_NOT_FOUND));

        Card card = new Card();
        card.setDeck(deck);
        card.setWord(word);
        card.setImageUrl(createCardDto.getImageUrl());
        cardRepo.save(card);
    }

    @Override
    public CardDetailDto update(Integer cardId, UpdateCardDto updateCardDto) {
        Card card = cardRepo.findById(cardId).orElseThrow(() -> new AppException(ResponseCode.CARD_NOT_FOUND));
        return cardMapper.toDetailDto(card);
    }

    @Override
    public void delete(Integer id) {
        cardRepo.deleteById(id);
    }

}
