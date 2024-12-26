package vn.edu.huce.beforeigner.infrastructures.historymodule.impls;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.domains.history.repo.LessonHistoryRepository;
import vn.edu.huce.beforeigner.infrastructures.historymodule.abstracts.ILessonHistoryService;
import vn.edu.huce.beforeigner.infrastructures.historymodule.dtos.LessonHistoryDto;
import vn.edu.huce.beforeigner.infrastructures.historymodule.mappers.LessonHistoryMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonHistoryService implements ILessonHistoryService {

    private final LessonHistoryRepository lessonHistoryRepo;

    private final LessonHistoryMapper lessonHistoryMapper;

    @Override
    @Transactional
    public List<LessonHistoryDto> getMyHistory(Account user) {
        return lessonHistoryRepo.findByOwner(user.getUsername()).stream()
                .map(lh -> lessonHistoryMapper.toDto(lh))
                .toList();
    }

}
