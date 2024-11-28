package vn.edu.huce.beforeigner.infrastructures.exammodule.impls;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.exam.LessonType;
import vn.edu.huce.beforeigner.domains.exam.QuestionType;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;
import vn.edu.huce.beforeigner.domains.history.LessonStatus;
import vn.edu.huce.beforeigner.domains.history.repo.LessonHistoryRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts.ILessonService;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.CompletedLessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDetailDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.LessonDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.questions.QuestionDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.mappers.AnswerMapper;
import vn.edu.huce.beforeigner.infrastructures.exammodule.mappers.LessonMapper;
import vn.edu.huce.beforeigner.infrastructures.exammodule.mappers.QuestionMapper;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {

	private final LessonHistoryRepository lessonHistoryRepo;

	private final LessonRepository lessonRepo;

	private final UserRepository userRepo;

	private final LessonMapper lessonMapper;

	private final AnswerMapper answerMapper;

	private final QuestionMapper questionMapper;

	@Override
	@Transactional
	public LessonDetailDto examine(Integer lessonId, User user) {
		if (user.getLearnCountAvailaible() < 1) {
			throw new AppException(ResponseCode.RETRY_COUNT_UNAVAILABLE);
		}
		Lesson lesson = lessonRepo.findById(lessonId)
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));
		
		switch (user.getPlan()) {
			case FREE:
				if (lesson.getType() == LessonType.PLUS_ONLY) {
					throw new AppException(ResponseCode.LESSON_IS_PLUS_ONLY);
				}
				user.setLearnCountAvailaible(user.getLearnCountAvailaible() - 1);
				userRepo.save(user);
				break;
			case PREMIUM_MONTH:
			case PREMIUM_YEAR:
				user.setLearnCountAvailaible(user.getLearnCountAvailaible() - 1);
				userRepo.save(user);
				break;
			default:
				break;
		}
		
		Optional<LessonHistory> optLessonHistory = lessonHistoryRepo.findByLessonAndOwner(lesson, user.getUsername());
		// Nếu đã học trước đó rồi thì ko làm gì
		if (optLessonHistory.isEmpty()) {
			// Nếu chưa học -> thêm 1 dòng mới
			LessonHistory history = new LessonHistory();
			history.setLesson(lesson);
			history.setStatus(LessonStatus.ONGOING);
			lessonHistoryRepo.save(history);
		}

		Set<QuestionDto> questionDtos = lesson.getQuestions().stream()
				.map(question -> {
					QuestionDto questionDto = questionMapper.toDto(question);
					if (question.getType() == QuestionType.GIVE_AUDIO_CHOOSE_WORD
							|| question.getType() == QuestionType.GIVE_MEAN_CHOOSE_WORD) {
						var options = question.getAnswers()
								.stream()
								.collect(Collectors.groupingBy(Answer::getIsTrue,
										Collectors.mapping(answerMapper::toOptionDto, Collectors.toList())));
						questionDto.option = options.get(true).stream()
								.findFirst()
								.orElse(null);
						questionDto.unrelatedOptions = options.get(false);
					}
					return questionDto;
				}).collect(Collectors.toSet());
		return lessonMapper.toDetailDto(lesson, questionDtos);
	}

	@Override
	public void completed(CompletedLessonDto completedLessonDto, User user) {
		Lesson lesson = lessonRepo.findById(completedLessonDto.getLessonId())
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));
		if (user.getIsFirstTry()) {
			user.setIsFirstTry(false);
			user.setStreakDays(user.getStreakDays()+1);
		}
		userRepo.save(user);
		LessonHistory lessonHistory = lessonHistoryRepo
				.findByLessonAndOwner(lesson, user.getUsername())
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_HISTORY_NOT_FOUND));
		if (lessonHistory.getStatus() == LessonStatus.ONGOING) {
			lessonHistory.setAccuracy(completedLessonDto.getAccuracy());
			lessonHistory.setTotalTime(Duration.between(LocalDateTime.now(), lessonHistory.getCreatedAt()).getSeconds());
			lessonHistory.setStatus(LessonStatus.COMPLETED);
			lessonHistoryRepo.save(lessonHistory);
		}
	}

	@Override
	public PagingResult<LessonDto> getSuggestedLessons(PagingRequest pagingRequest, User user) {
		return PagingResult.of(
				lessonRepo.findByLevel(pagingRequest.pageable(), user.getLevel()),
				lesson -> lessonMapper.toDto(lesson));
	}
}
