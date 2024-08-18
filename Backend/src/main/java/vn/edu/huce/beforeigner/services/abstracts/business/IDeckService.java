package vn.edu.huce.beforeigner.services.abstracts.business;

import java.util.List;

import vn.edu.huce.beforeigner.dtos.bussiness.DeckDto;
import vn.edu.huce.beforeigner.dtos.bussiness.creatation.CreateDeckDto;
import vn.edu.huce.beforeigner.dtos.bussiness.detail.DeckDetailDto;
import vn.edu.huce.beforeigner.dtos.bussiness.updatation.UpdateDeckDto;

public interface IDeckService {
    List<DeckDto> getAll(Integer categoryId, String keyword);

    DeckDetailDto getById(Integer id);

    void addNew(CreateDeckDto createDeckDto);

    DeckDetailDto update(Integer deckId, UpdateDeckDto updateDeckDto);

    void delete(Integer id);
}
