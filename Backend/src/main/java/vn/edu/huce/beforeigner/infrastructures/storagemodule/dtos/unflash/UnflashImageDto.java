package vn.edu.huce.beforeigner.infrastructures.storagemodule.dtos.unflash;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnflashImageDto {
    private int total;
    private int totalPages;
    private List<UnflashImageResultDto> results;
}
