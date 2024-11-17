package vn.edu.huce.beforeigner.infrastructures.exammodule.impls;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.core.SubscriptionPlan;
import vn.edu.huce.beforeigner.domains.core.User;
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
import vn.edu.huce.beforeigner.utils.LessonUtil;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {

	private final LessonHistoryRepository lessonHistoryRepo;

	private final LessonRepository lessonRepo;

	private final LessonMapper lessonMapper;

	private final AnswerMapper answerMapper;

	@Override
	public LessonDetailDto examine(Integer lessonId, User user) {
		Lesson lesson = lessonRepo.findById(lessonId)
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));
		if (lesson.getType() == LessonType.PLUS_ONLY
				&& user.getPlan() == SubscriptionPlan.FREE) {
			throw new AppException(ResponseCode.LESSON_IS_PLUS_ONLY);
		}
		Optional<LessonHistory> optLessonHistory = lessonHistoryRepo.findByLessonAndOwner(lesson, user.getUsername());
		// Nếu đã học trước đó rồi thì ko làm gì
		if (optLessonHistory.isEmpty()) {
			// Nếu chưa học -> thêm 1 dòng mới
			LessonHistory history = new LessonHistory();
			history.setLesson(lesson);
			lessonHistoryRepo.save(history);
		}

		Set<QuestionDto> questionDtos = lesson.getQuestions().stream()
				.map(question -> {
					QuestionDto questionDto = QuestionDto.builder()
							.type(question.getType())
							.sentenseMeaning(question.getSentenseMeaning()) // đề
							.sentenseAudio(question.getSentenseAudio()) // đề
							.sentenseWords(Optional.ofNullable(question.getSentenseWords()).map(sw -> sw.split(" "))
									.orElse(null)) // các từ của đáp án
							.unrelatedWords(Optional.ofNullable(question.getUnrelatedWords()).map(uw -> uw.split(" "))
									.orElse(null)) // từ ko liên quan của đáp án
							.matchingAnswers(Optional.ofNullable(question.getMatching())
									.map(match -> LessonUtil.decode(match)).orElse(null))
							.build();

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
		LessonHistory lessonHistory = lessonHistoryRepo
				.findByLessonAndOwner(lesson, user.getUsername())
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_HISTORY_NOT_FOUND));
		if (lessonHistory.getStatus() == LessonStatus.ONGOING) {
			lessonHistory.setAccuracy(completedLessonDto.getAccuracy());
			lessonHistory.setTotalTime(Duration.between(LocalDateTime.now(), lessonHistory.getCreatedAt()));
			lessonHistory.setStatus(LessonStatus.COMPLETED);
			lessonHistoryRepo.save(lessonHistory);
		}
	}

	@Override
	public PagingResult<LessonDto> getSuggestedLessons(PagingRequest pagingRequest, User user) {
		return PagingResult.of(
				lessonRepo.getRecentLessonsAndLessonsWithSameLevel(pagingRequest.pageable(), user.getUsername(),
						user.getLevel()),
				lesson -> {
					return LessonDto.builder()
							.id(lesson.getId())
							.name(lesson.getName())
							.cover(lesson.getCoverImage().getUrl())
							.type(lesson.getType())
							.status(lesson.getLessonHistories().stream()
									.findFirst().get().getStatus())
							.build();
				});
	}
}
