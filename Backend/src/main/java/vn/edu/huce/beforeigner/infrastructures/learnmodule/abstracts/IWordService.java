package vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.entities.learn.WordType;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.updatation.UpdateWordDto;

public interface IWordService {

    List<WordDto> getAll(int deckId, WordType wordType);

    WordDetailDto getById(int id);

    void addNew(CreateWordDto createWordDto);

    WordDetailDto update(int wordId, UpdateWordDto updateWordDto);

    void delete(int id);
}
