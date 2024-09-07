package vn.edu.huce.beforeigner.infrastructures.learnmodule.mappers;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.Word;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail.WordDetailDto;

@Component
@AllArgsConstructor
public class WordMapper {

    private ExampleMapper exampleMapper;
    
    public WordDto toDto(Word word) {
        return WordDto.builder()
            .id(word.getId())
            .value(word.getValue())
            .build();
    }

    public WordDetailDto toDetail(Word word) {
        return WordDetailDto.builder()
            .id(word.getId())
            .value(word.getValue())
            .examples(exampleMapper.toListString(word.getExamples()))
            .audioFile(word.getAudioFile())
            .wordType(word.getWordType())
            .phonetic(word.getPhonetic())
            .build();
    }
}
