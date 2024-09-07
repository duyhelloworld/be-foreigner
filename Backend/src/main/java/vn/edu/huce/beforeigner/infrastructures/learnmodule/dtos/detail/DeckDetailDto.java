package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.CardDto;

@Data
@Builder
public class DeckDetailDto {
    private Integer id;

    private String name;

    private String description;

    private List<CardDto> cards;
}
