package vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation;

import java.util.List;

import lombok.Data;

@Data
public class CreateDeckDto {
    private String name;

    private String description;

    private List<Integer> cardIds;
}
