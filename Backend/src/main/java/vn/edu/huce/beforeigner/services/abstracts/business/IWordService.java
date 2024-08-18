package vn.edu.huce.beforeigner.services.abstracts.business;

import java.util.List;

import vn.edu.huce.beforeigner.dtos.bussiness.WordDto;
import vn.edu.huce.beforeigner.dtos.bussiness.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.dtos.bussiness.detail.WordDetailDto;
import vn.edu.huce.beforeigner.dtos.bussiness.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.entities.learn.WordType;

public interface IWordService {
    List<WordDto> getAll(String keyword, Integer categoryId, WordType wordType);

    WordDetailDto getById(Integer id);

    void addNew(CreateWordDto createWordDto);

    WordDetailDto update(Integer wordId, UpdateWordDto updateWordDto);

    void delete(Integer id);
}
