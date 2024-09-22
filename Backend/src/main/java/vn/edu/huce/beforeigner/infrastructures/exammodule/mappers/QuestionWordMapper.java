package vn.edu.huce.beforeigner.infrastructures.exammodule.mappers;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.exam.QuestionWord;
import vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.QuestionWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers.WordMapper;

@Component
@AllArgsConstructor
public class QuestionWordMapper {

    private WordMapper wordMapper;

    public QuestionWordDto toDto(QuestionWord questionWord) {
        return QuestionWordDto.builder()
            .index(questionWord.getIndexOfWord())
            .wordDto(wordMapper.toDto(questionWord.getWord()))
            .build();
    }
    
}
