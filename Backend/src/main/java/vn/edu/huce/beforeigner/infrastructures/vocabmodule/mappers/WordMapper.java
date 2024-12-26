package vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.vocab.Sentense;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;

@Component
public class WordMapper {

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
                .examples(word.getSentenses().stream()
                        .map(s -> getExamples(s))
                        .toList())
                .build();
    }

    public Map<String, String> getExamples(Sentense sentense) {
        Map<String, String> map = new HashMap<>();
        map.put(sentense.getValue(), sentense.getMean());
        return map;
    }
}
