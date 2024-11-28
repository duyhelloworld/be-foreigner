package vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;

@Component
public class WordMapper {

    @Autowired
    private ExampleMapper exampleMapper;

    public WordDto toDto(Word word) {
        return WordDto.builder()
                .id(word.getId())
                .image(word.getImageUrl())
                .audio(word.getAudioUrl())
                .value(word.getValue())
                .mean(word.getMean())
                .phonetic(word.getPhonetic())
                .build();
    }

    public WordDetailDto toDetailDto(Word word) {
        return WordDetailDto.builder()
                .id(word.getId())
                .image(word.getImageUrl())
                .audio(word.getAudioUrl())
                .value(word.getValue())
                .phonetic(word.getPhonetic())
                .examples(word.getExamples().stream()
                        .map(e -> exampleMapper.toDetailDto(e))
                        .collect(Collectors.toSet()))
                .build();
    }
}
