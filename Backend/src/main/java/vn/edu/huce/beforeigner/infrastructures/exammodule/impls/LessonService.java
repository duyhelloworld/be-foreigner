package vn.edu.huce.beforeigner.infrastructures.exammodule.impls;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.configurations.AuditorConfig;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
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

	private final QuestionMapper questionMapper;

	private final AnswerMapper answerMapper;

	@Override
	@Transactional
	public LessonDetailDto examine(Integer lessonId, User user) {
		// if (user.getLearnCountAvailaible() < 1) {
		// 	throw new AppException(ResponseCode.RETRY_COUNT_UNAVAILABLE);
		// }

		Lesson lesson = lessonRepo.findById(lessonId)
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));

		// switch (user.getPlan()) {
		// 	case FREE:
		// 		if (lesson.getType() == LessonType.PLUS_ONLY) {
		// 			throw new AppException(ResponseCode.LESSON_IS_PLUS_ONLY);
		// 		}
		// 	case PREMIUM_MONTH:
		// 	case PREMIUM_YEAR:
		// 		user.setLearnCountAvailaible(user.getLearnCountAvailaible() - 1);
		// 		userRepo.save(user);
		// 		break;
		// 	default:
		// 		break;
		// }

		int historyId = 0;
		var learntHistory = lesson.getLessonHistories().stream()
				.filter(lh -> AuditorConfig.getAuditor(user).equals(lh.getOwner())
						&& lh.getStatus() != LessonStatus.COMPLETED)
				.findFirst();

		// Nếu chưa học -> thêm 1 dòng mới
		if (learntHistory.isEmpty()) {
			LessonHistory newHistory = new LessonHistory();
			newHistory.setLesson(lesson);
			newHistory.setStatus(LessonStatus.ONGOING);
			lessonHistoryRepo.save(newHistory);
			historyId = newHistory.getId();
		} else {
			historyId = learntHistory.get().getId();
		}

		Set<QuestionDto> questionDtos = lesson.getQuestions().stream()
				.map(question -> {
					QuestionDto questionDto = questionMapper.toDto(question);
					if (question.getType() == QuestionType.GIVE_AUDIO_CHOOSE_WORD
							|| question.getType() == QuestionType.GIVE_MEAN_CHOOSE_WORD) {
						var correct = question.getAnswers().stream()
							.filter(a -> a.isTrue())
							.findFirst()
							.map(a -> a.getWord())
							.orElseThrow(() -> new AppException(ResponseCode.UNEXPECTED_ERROR));
						questionDto.correctOptionMean = correct.getMean();
						questionDto.correctOptionValue = correct.getValue();
						questionDto.correctOptionAudio = correct.getAudioUrl();
						questionDto.answerOptions = question.getAnswers()
								.stream().map(a -> answerMapper.toOptionDto(a))
								.toList();
					}
					return questionDto;
				}).collect(Collectors.toSet());
		return lessonMapper.toDetailDto(lesson, questionDtos, historyId);
	}

	@Override
	@Transactional
	public void completed(CompletedLessonDto completedLessonDto, User user) {
		LessonHistory lessonHistory = lessonHistoryRepo.findById(completedLessonDto.getHistoryId())
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));
		if (user.getIsFirstTry()) {
			user.setIsFirstTry(false);
			user.setStreakDays(user.getStreakDays() + 1);
			userRepo.save(user);
		}
		if (lessonHistory.getStatus() == LessonStatus.ONGOING) {
			lessonHistory.setAccuracy(completedLessonDto.getAccuracy());
			lessonHistory
				.setTotalTime(Duration.between(LocalDateTime.now(), lessonHistory.getCreatedAt()).getSeconds());
			lessonHistory.setStatus(LessonStatus.COMPLETED);
			lessonHistoryRepo.save(lessonHistory);
		}
	}

	@Override
	@Transactional
	public PagingResult<LessonDto> getSuggestedLessons(PagingRequest pagingRequest, User user) {
		return PagingResult.of(
				lessonRepo.findByLevel(pagingRequest.pageable(), user.getLevel()),
				lesson -> lessonMapper.toDto(lesson));
	}
}
