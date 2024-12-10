package vn.edu.huce.beforeigner.infrastructures.coremodule.dtos;

import lombok.Builder;

@Builder
public class StreakDto {

    public boolean hasLearned;

    public int streakDays;
}
