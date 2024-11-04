package vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.ExampleDto;

@Component
public class ExampleMapper {

    public ExampleDto toDetailDto(Example example) {
        return ExampleDto.builder()
                .sentense(example.getSentense())
                .mean(example.getMean())
                .build();
    }

}
