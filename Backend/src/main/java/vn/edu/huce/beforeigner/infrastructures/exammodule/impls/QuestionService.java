package vn.edu.huce.beforeigner.infrastructures.exammodule.impls;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.domains.exam.Question;
import vn.edu.huce.beforeigner.domains.exam.repo.QuestionRepository;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts.IQuestionService;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.QuestionDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.creation.CreateQuestionDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.mappers.QuestionMapper;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepo;

    private final WordRepository wordRepo;

    private final QuestionMapper questionMapper;

    private final AppObjectMapper objectMapper;

    @Override
    public PagingResult<QuestionDto> getAll(PagingRequest pagingRequest) {
        return PagingResult.of(questionRepo.findAll(pagingRequest.pageable()), q -> questionMapper.toDto(q));
    }

    @Override
    public QuestionDto getById(Integer id) {
        return questionMapper
                .toDto(questionRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.QUESTION_NOT_FOUND)));
    }

    @Override
    public QuestionDto addNew(CreateQuestionDto createQuestionDto) {
        Word word = wordRepo.findById(createQuestionDto.getWordId())
                .orElseThrow(() -> new AppException(ResponseCode.WORD_NOT_FOUND));
                log.info(objectMapper.toJson(word));
        // Question question = Question.builder()
        //     .answers();
        // question.setWord(word);
        
        // Set<Answer> answers = new HashSet<>();
        //         createQuestionDto.getAnswers().stream()
        //             .filter(a -> a.getIndex().equals(createAnswerDto.getMatchIndex()))
        //             .findFirst()
        //             .ifPresent(a -> {
        //                 Answer matchAnswer = new Answer();
        //                 matchAnswer.setQuestion(question);
        //                 // Đặt Cascade=MERGE để save được matchAnswer
        //                 answer.setMatchAnswer(matchAnswer);
        //             });
        //     answers.add(answer);
        // }
        
        // questionRepo.save(question);
        // log.info("Added new question : {}", question);
        // return questionMapper.toDto(question);
        return null;
    }

    @Override
    public void delete(Integer id) {
        Question question = questionRepo.findById(id)
                .orElseThrow(() -> new AppException(ResponseCode.QUESTION_NOT_FOUND));
        questionRepo.delete(question);
    }
}
