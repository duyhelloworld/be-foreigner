package vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts;

import vn.edu.huce.beforeigner.infrastructures.paging.PagingRequest;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingResult;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateWordDto;

public interface IWordService {
    
    PagingResult<WordDto> getAll(PagingRequest pagingRequest, Integer topicId);

    WordDetailDto getById(Integer id);

    void addNew(CreateWordDto createWordDto);

    WordDetailDto update(Integer id, UpdateWordDto updateWordDto);

    void delete(Integer id);
}
