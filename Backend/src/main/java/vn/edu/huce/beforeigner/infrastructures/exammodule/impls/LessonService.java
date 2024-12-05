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
import vn.edu.huce.beforeigner.constants.LessonConstants;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.core.repo.UserRepository;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import vn.edu.huce.beforeigner.domains.exam.repo.LessonRepository;
import vn.edu.huce.beforeigner.domains.history.LessonHistory;
import vn.edu.huce.beforeigner.domains.history.LessonStatus;
import vn.edu.huce.beforeigner.domains.history.repo.LessonHistoryRepository;
import vn.edu.huce.beforeigner.domains.leaderboard.LeaderBoardUser;
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
		Lesson lesson = lessonRepo.findById(lessonId)
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));

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
			// Nếu đã học rồi và chưa hoàn thành thì lấy mã lịch sử để trả về
			historyId = learntHistory
					.filter(lh -> lh.getStatus() == LessonStatus.ONGOING)
					.map(lh -> lh.getId())
					.get();
		}

		Set<QuestionDto> questionDtos = lesson.getQuestions().stream()
				.map(question -> {
					var questionDtoBuilder = questionMapper.toDto(question);
					if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
						var correct = question.getAnswers().stream()
								.filter(a -> a.isTrue())
								.findFirst()
								.map(a -> a.getWord()).get();
						switch (question.getType()) {
							case LEARN_WORD:
							case GIVE_MEAN_ENTER_WORD:
								questionDtoBuilder.correctOptionValue(correct.getValue());
								questionDtoBuilder.correctOptionMean(correct.getMean());
								questionDtoBuilder.correctOptionAudio(correct.getAudioUrl());
								break;
							case GIVE_AUDIO_ENTER_WORD:
								questionDtoBuilder.correctOptionValue(correct.getValue());
								break;
							case GIVE_AUDIO_CHOOSE_WORD:
								questionDtoBuilder.correctOptionAudio(
										correct.getAudioUrl());
								questionDtoBuilder.answerOptions(question.getAnswers()
										.stream().map(a -> answerMapper.toOptionDto(a))
										.toList());
								break;
							case GIVE_MEAN_CHOOSE_WORD:
								questionDtoBuilder.correctOptionMean(correct.getMean());
								questionDtoBuilder.answerOptions(question.getAnswers()
										.stream().map(a -> answerMapper.toOptionDto(a))
										.toList());
								break;
							default:
								break;
						}
					}
					return questionDtoBuilder.build();
				}).collect(Collectors.toSet());
		return lessonMapper.toDetailDto(lesson, questionDtos, historyId);
	}

	@Override
	@Transactional
	public void completed(CompletedLessonDto completedLessonDto, User user) {
		LessonHistory lessonHistory = lessonHistoryRepo.findById(completedLessonDto.getHistoryId())
				.orElseThrow(() -> new AppException(ResponseCode.LESSON_NOT_FOUND));
		if (lessonHistory.getStatus() == LessonStatus.COMPLETED) {
			throw new AppException(ResponseCode.LESSON_ALREADY_COMPLETED);
		}
		int userElo = lessonHistory.getLesson().getTarget().getElo();
		if (completedLessonDto.getAccuracy() > LessonConstants.ACCURACY_TO_SUCCESS) {
			userElo += LessonConstants.BONUS_ELO_WHEN_SUCCESS;
		}
		for (LeaderBoardUser leaderBoardUser : user.getLeaderBoardUsers()) {
			leaderBoardUser.setElo(leaderBoardUser.getElo() + userElo);
		}
		userRepo.save(user);
		if (lessonHistory.getStatus() == LessonStatus.ONGOING) {
			lessonHistory.setAccuracy(completedLessonDto.getAccuracy());
			lessonHistory.setElo(userElo);
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
