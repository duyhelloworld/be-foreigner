package vn.edu.huce.beforeigner.infrastructures.exammodule.dtos.creation;

import java.util.List;

import lombok.Data;

@Data
public class CreateAnswerDto {

    private Integer index;

    private Integer matchIndex;

    private List<String> fillInBlankWords;

    private Boolean isTrue;
}
