package vn.edu.huce.beforeigner.dtos.bussiness;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeckDto {
    
    private Integer id;

    private String name;
}
