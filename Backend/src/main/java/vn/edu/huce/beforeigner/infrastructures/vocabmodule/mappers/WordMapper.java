package vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;

@Component
public class WordMapper {

    @Autowired
    private ExampleMapper exampleMapper;

    @Lazy
    private TopicMapper topicMapper;

    public WordDto toDto(Word word) {
        return WordDto.builder()
                .id(word.getId())
                .image(word.getImage())
                .audio(word.getAudio())
                .mean(word.getMean())
                .value(word.getValue())
                .phonetic(word.getPhonetic())
                .build();
    }

    public WordDetailDto toDetailDto(Word word) {
        return WordDetailDto.builder()
                .id(word.getId())
                .image(word.getImage())
                .audio(word.getAudio())
                .mean(word.getMean())
                .value(word.getValue())
                .phonetic(word.getPhonetic())
                .topic(word.getTopics().stream()
                        .map(t -> topicMapper.toDto(t))
                        .collect(Collectors.toSet()))
                .examples(word.getExamples().stream()
                        .map(e -> exampleMapper.toDetailDto(e))
                        .collect(Collectors.toSet()))
                .build();
    }
}
