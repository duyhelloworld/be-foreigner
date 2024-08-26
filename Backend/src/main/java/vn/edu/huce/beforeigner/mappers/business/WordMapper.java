package vn.edu.huce.beforeigner.mappers.business;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.entities.learn.Word;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.WordDetailDto;
import vn.edu.huce.beforeigner.entities.learn.MeanExample;

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
                toDto(word.getWordExamples()))
            .audioFile(word.getAudioFile())
            .phonetic(word.getPhonetic())
            .build();
    }

    public Map<String, String> toDto(List<MeanExample> wordExamples) {
        Map<String, String> result = new HashMap<>();
        for (MeanExample wordExample : wordExamples) {
            result.put(wordExample.getMean(), wordExample.getExample());
        }
        return result;
    }

    public List<MeanExample> toEntity(Map<String, String> input) {
        List<MeanExample> wordExamples = new LinkedList<>();
        for (var entry : input.entrySet()) {
            MeanExample wordExample = new MeanExample();
            wordExample.setMean(entry.getKey());
            wordExample.setExample(entry.getValue());
            wordExamples.add(wordExample);
        }
        return wordExamples;
    }
}
