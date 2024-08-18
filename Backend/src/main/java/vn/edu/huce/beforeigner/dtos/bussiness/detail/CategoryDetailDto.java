package vn.edu.huce.beforeigner.dtos.bussiness.detail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDetailDto {
    
    private Integer id;

    private String name;

    private String description;
}
