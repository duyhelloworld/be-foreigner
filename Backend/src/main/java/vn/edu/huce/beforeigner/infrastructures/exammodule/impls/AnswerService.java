package vn.edu.huce.beforeigner.infrastructures.exammodule.impls;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.exam.repo.AnswerRepository;
import vn.edu.huce.beforeigner.infrastructures.exammodule.abstracts.IAnswerService;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.AnswerDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.updatation.UpdateAnswerDto;
import vn.edu.huce.beforeigner.infrastructures.exammodule.mappers.AnswerMapper;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService implements IAnswerService {

    private final AnswerRepository answerRepo;

    private final AnswerMapper answerMapper;

    @Override
    public Set<AnswerDto> getAllByQuestion(Integer questionId) {
        return answerRepo.findByQuestionId(questionId)
                .stream().map(a -> answerMapper.toDto(a)).collect(Collectors.toSet());
    }

    @Override
    public AnswerDto update(UpdateAnswerDto updateAnswerDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Set<Integer> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
