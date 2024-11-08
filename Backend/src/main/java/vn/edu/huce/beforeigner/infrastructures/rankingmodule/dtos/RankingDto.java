package vn.edu.huce.beforeigner.infrastructures.rankingmodule.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public class RankingDto {
    
    public List<RankedUserDto> users;

    public LocalDateTime fetchedOn;
}
