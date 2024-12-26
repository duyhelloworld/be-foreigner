package vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts;

import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

public interface IWordService {
    
    /**
     * Lấy từ vựng ngày hôm nay
     * @param user
     * @return
     */
    WordDto getTodayWord(Account user);

    /**
     * Lấy tất cả từ vựng với phân trang
     * @param pagingRequest
     * @return
     */
    PagingResult<WordDto> getAll(PagingRequest pagingRequest);

    /**
     * Lấy thông tin chi tiết
     * @param id
     * @return
     */
    WordDetailDto getDetailById(Integer id);

    /**
     * Tạo mới từ vựng
     * @param createWordDto
     */
    void addNew(CreateWordDto createWordDto);

    /**
     * Cập nhật từ vựng
     * @param id
     * @param updateWordDto
     * @return
     */
    WordDetailDto update(Integer id, UpdateWordDto updateWordDto);

    /**
     * Xóa từ vựng
     * @param id
     */
    void delete(Integer id);
}
