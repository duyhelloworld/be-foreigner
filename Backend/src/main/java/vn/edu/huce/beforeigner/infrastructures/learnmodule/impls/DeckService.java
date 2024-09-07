package vn.edu.huce.beforeigner.infrastructures.learnmodule.impls;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.Card;
import vn.edu.huce.beforeigner.entities.learn.Deck;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.IDeckService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.DeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.creatation.CreateDeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail.DeckDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.updatation.UpdateDeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.mappers.DeckMapper;
import vn.edu.huce.beforeigner.repositories.CardRepository;
import vn.edu.huce.beforeigner.repositories.DeckRepository;

@Service
@AllArgsConstructor
public class DeckService implements IDeckService {

    private DeckRepository deckRepo;

    private CardRepository cardRepo;

    private DeckMapper deckMapper;

    @Override
    public List<DeckDto> getAll() {
        return deckRepo.findAll().stream().map(d -> deckMapper.toDto(d)).toList();
    }

    @Override
    public DeckDetailDto getById(Integer id) {
        return deckMapper.toDetailDto(deckRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.DECK_NOT_FOUND)));
    }

    @Override
    public void addNew(CreateDeckDto createDeckDto) {
        Set<Card> cards = cardRepo.findByIdIn(createDeckDto.getCardIds());
        Deck deck = new Deck();
        deck.setCards(cards);
        deck.setDescription(createDeckDto.getDescription());
        deck.setName(createDeckDto.getName());
        deckRepo.save(deck);
    }

    @Override
    public DeckDetailDto update(Integer deckId, UpdateDeckDto updateDeckDto) {
        return deckMapper.toDetailDto(null);
    }

    @Override
    public void delete(Integer id) {
        deckRepo.deleteById(id);
    }
    
}
