package vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.ExampleDetailDto;

@Component
public class ExampleMapper {

    public ExampleDetailDto toDetailDto(Example example) {
        return ExampleDetailDto.builder()
                .sentense(example.getSentense())
                .mean(example.getMean())
                .build();
    }

}
