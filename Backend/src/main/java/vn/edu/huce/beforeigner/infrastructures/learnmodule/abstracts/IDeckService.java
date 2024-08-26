package vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.DeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation.CreateDeckDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.DeckDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.updatation.UpdateDeckDto;

public interface IDeckService {
    List<DeckDto> getAll();

    DeckDetailDto getById(Integer id);

    void addNew(CreateDeckDto createDeckDto);

    DeckDetailDto update(Integer deckId, UpdateDeckDto updateDeckDto);

    void delete(Integer id);
}
