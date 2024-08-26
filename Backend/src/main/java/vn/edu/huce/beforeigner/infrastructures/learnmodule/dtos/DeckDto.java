package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeckDto {
    
    private Integer id;

    private String name;
}
