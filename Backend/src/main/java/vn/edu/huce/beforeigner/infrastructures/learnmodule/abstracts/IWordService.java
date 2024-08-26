package vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.entities.learn.WordType;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.updatation.UpdateWordDto;

public interface IWordService {
    List<WordDto> getAll(String keyword, Integer categoryId, WordType wordType);

    WordDetailDto getById(Integer id);

    void addNew(CreateWordDto createWordDto);

    WordDetailDto update(Integer wordId, UpdateWordDto updateWordDto);

    void delete(Integer id);
}
