package vn.edu.huce.beforeigner.infrastructures.exammodule.impls;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.exam.DifficultyLevel;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.coremodule.dtos.UserDto;
import vn.edu.huce.beforeigner.infrastructures.coremodule.mappers.UserMapper;
import vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts.ILessonService;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.creation.CreateLessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.detail.LessonDetailDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.updatation.UpdateLessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.mappers.LessonMapper;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingRequest;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingResult;
import vn.edu.huce.beforeigner.utils.NumberUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LessonService implements ILessonService {

    private final LessonRepository lessonRepo;

    private final LessonMapper lessonMapper;

    private final UserMapper userMapper;

    @Override
    public LessonDetailDto learn(Integer lessonId, User user) {
        Lesson lesson;
        if (NumberUtils.notNullAndGreaterThanZero(lessonId)) {
            lesson = lessonRepo.findById(lessonId)
                    .orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));
        } else {
            // Random lesson base on user level
            lesson = lessonRepo.findFirstByDiffLevel(extractDiffLevel(user))
                    .orElseThrow(() -> new AppException(ResponseCode.NO_LESSON_HAVE_THIS_DIFF_LEVEL));
        }
        Set<UserDto> userDtos = lesson.getUserLessonStatistics()
                .stream()
                .map(s -> userMapper.toDto(s.getUser()))
                .collect(Collectors.toSet());
        return lessonMapper.toDetailDto(lesson, userDtos);
    }

    @Override
    public PagingResult<LessonDto> getAll(PagingRequest pagingRequest) {
        return PagingResult.of(lessonRepo.findAll(pagingRequest.pageable()), l -> lessonMapper.toDto(l));
    }

    @Override
    public void addNew(CreateLessonDto createLessonDto) {
        // Lesson lesson = new Lesson();
    }

    @Override
    public LessonDto update(Integer id, UpdateLessonDto updateLessonDto) {
        return lessonMapper.toDto(lessonRepo.findById(id)
                .orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND)));
    }

    @Override
    public void delete(Integer id) {
        Lesson lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));
        lessonRepo.delete(lesson);
    }

    private DifficultyLevel extractDiffLevel(User user) {
        return DifficultyLevel.valueOf(user.getLevel().name());
    }
}