package vn.edu.huce.beforeigner.infrastructures.exammodule.impls;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.exam.Question;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.domains.system.SysvarKey;
import vn.edu.huce.beforeigner.domains.system.repo.SysvarRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts.ILessonService;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDetailDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.questions.QuestionDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.mappers.AnswerMapper;
import vn.edu.huce.beforeigner.infrastructures.exammodule.mappers.LessonMapper;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {

    private final LessonRepository lessonRepo;

    private final UserRepository userRepo;

    private final SysvarRepository systemVariableRepo;

    private final LessonMapper lessonMapper;

    private final AnswerMapper answerMapper;

    @Override
    public LessonDetailDto examine(Integer lessonId, User user) {
        Lesson lesson = lessonRepo.findById(lessonId)
                    .orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));
        Set<QuestionDto> questionDtos = new HashSet<>();
        for (Question question : lesson.getQuestions()) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setType(question.getType());

            switch (question.getType()) {
                case GIVE_AUDIO_CHOOSE_WORD:
                    // sau fix bằng groupBy  
                    questionDto.setCorrectOption(answerMapper.toOptionDto(question.getAnswers().stream()
                        .filter(a -> a.getIsTrue())
                        .findFirst().orElse(null)));
                    questionDto.setIncorrectOptions(question.getAnswers().stream()
                        .filter(a -> !a.getIsTrue())
                        .map(a -> answerMapper.toOptionDto(a))
                        .collect(Collectors.toSet()));
                    break;
                case GIVE_AUDIO_REARRANGE_WORDS:
                    questionDto.setCorrectSentenseAudio(question.getSentenseAudio());
                    questionDto.setCorrectSentenseWords(question.getAnswers().stream()
                        .sorted((a1, a2) -> a1.getIndex().compareTo(a2.getIndex()))
                        .map(a -> a.getTxt())
                        .collect(Collectors.toSet()));
                    break;
                case GIVE_MEAN_CHOOSE_WORD:
                    // var answers = question.getAnswers().stream().collect(Collectors.);
                    questionDto.setCorrectOption(answerMapper.toOptionDto(question.getAnswers().stream()
                        .filter(a -> a.getIsTrue()).findFirst().orElse(null)));
                    questionDto.setIncorrectOptions(question.getAnswers().stream()
                        .filter(a -> !a.getIsTrue())
                        .map(a -> answerMapper.toOptionDto(a))
                        .collect(Collectors.toSet()));
                    break;
                case GIVE_SENTENSE_REARRANGE_WORDS:
                    questionDto.setCorrectSentenseWords(question.getAnswers().stream()
                        .filter(a -> a.getIsTrue())
                        .sorted((a1, a2) -> a1.getIndex().compareTo(a2.getIndex()))
                        .map(a -> a.getTxt())
                        .collect(Collectors.toSet()));
                    questionDto.setUnrelatedWords(question.getAnswers().stream()
                        .filter(a -> !a.getIsTrue())
                        .map(a -> a.getTxt())
                        .collect(Collectors.toSet()));
                    break;
                case MATCHING:
                    questionDto.setMatchingAnswers(question.getAnswers().stream()
                        .map(a -> a.getTxt().split(":"))
                        .collect(Collectors.toMap(a -> a[0], b -> b[1])));
                default:
                    break;
            }
            questionDtos.add(questionDto);
        }
        return lessonMapper.toDetailDto(lesson, questionDtos);
    }

    @Override
    @Transactional
    public void retry(Integer questionId, User user) {
        Integer currentUserDiamonds = user.getDiamonds();
        Integer requiredRetryDiamonds = systemVariableRepo.findByKey(SysvarKey.DIAMONDS_PER_RETRY)
            .map(sv -> Integer.parseInt(sv.getValue()))
            .orElseThrow(() -> new AppException(ResponseCode.SYSTEM_VARIABLE_INVALID_DATA));

        if (currentUserDiamonds < requiredRetryDiamonds) {
            throw new AppException(ResponseCode.NOT_ENOUGH_DIAMOND);
        }
        user.setDiamonds(currentUserDiamonds - requiredRetryDiamonds);
        userRepo.save(user);
    }

    @Override
    public PagingResult<LessonDto> getSuggestedLessons(PagingRequest pagingRequest, User user) {
        return PagingResult.of(lessonRepo.findByUser(pagingRequest.pageable(), user), lesson -> lessonMapper.toDto(lesson));
    }
}
