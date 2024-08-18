package vn.edu.huce.beforeigner.dtos.bussiness.detail;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import vn.edu.huce.beforeigner.dtos.bussiness.CardDto;

@Data
@Builder
public class DeckDetailDto {
    private Integer id;

    private String name;

    private String description;

    private List<CardDto> cards;
}
