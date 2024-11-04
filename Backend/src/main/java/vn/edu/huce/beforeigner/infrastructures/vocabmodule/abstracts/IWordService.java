package vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

public interface IWordService {
    
    WordDto getTodayWord(User user);

    PagingResult<WordDto> getAll(PagingRequest pagingRequest);

    WordDetailDto getDetailById(Integer id);

    void addNew(CreateWordDto createWordDto);

    WordDetailDto update(Integer id, UpdateWordDto updateWordDto);

    void delete(Integer id);
}
