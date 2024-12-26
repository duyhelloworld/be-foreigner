package vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.vocab.Sentense;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.SentenseDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.SentenseDetailDto;

@Component
public class SentenseMapper {
    public SentenseDto toDto(Sentense sentense) {
        return SentenseDto.builder()
                .id(sentense.getId())
                .value(sentense.getValue())
                .mean(sentense.getMean())
                .build();
    }

    public SentenseDetailDto toDetailDto(Sentense sentense) {
        return SentenseDetailDto.builder()
                .id(sentense.getId())
                .audio(sentense.getAudioUrl())
                .value(sentense.getValue())
                .mean(sentense.getMean())
                .build();
    }
}
