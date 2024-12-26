package vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts;

import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.SentenseDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateSentenseDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.SentenseDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateSentenseDto;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

public interface ISentenseService {

    /**
     * Lấy tất cả câu với phân trang
     * @param pagingRequest
     * @return
     */
    PagingResult<SentenseDto> getAll(PagingRequest pagingRequest);

    /**
     * Lấy thông tin chi tiết
     * @param id
     * @return
     */
    SentenseDetailDto getDetailById(Integer id);

    /**
     * Tạo mới câu
     * @param createSentenseDto
     */
    void addNew(CreateSentenseDto createSentenseDto);

    /**
     * Cập nhật câu
     * @param id
     * @param updateSentenseDto
     * @return
     */
    SentenseDetailDto update(Integer id, UpdateSentenseDto updateSentenseDto);

    /**
     * Xóa câu
     * @param id
     */
    void delete(Integer id);
}
