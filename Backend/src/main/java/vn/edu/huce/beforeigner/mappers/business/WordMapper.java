package vn.edu.huce.beforeigner.mappers.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.dtos.bussiness.WordDto;
import vn.edu.huce.beforeigner.dtos.bussiness.detail.WordDetailDto;
import vn.edu.huce.beforeigner.entities.learn.Word;
import vn.edu.huce.beforeigner.entities.learn.WordExample;

@Component
public class WordMapper {
    
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
            .meanAndExamples(
                wordMeanAndExample(word.getWordExamples()))
            .audioFile(word.getAudioFile())
            .pronunciation(word.getPronunciation())
            .build();
    }

    private Map<String, String> wordMeanAndExample(List<WordExample> wordExamples) {
        Map<String, String> result = new HashMap<>();
        for (WordExample wordExample : wordExamples) {
            result.put(wordExample.getMeaning(), wordExample.getSentence());
        }
        return result;
    }
}
