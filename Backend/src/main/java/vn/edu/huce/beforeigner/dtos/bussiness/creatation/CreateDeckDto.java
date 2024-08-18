package vn.edu.huce.beforeigner.dtos.bussiness.creatation;

import java.util.Set;

import lombok.Data;

@Data
public class CreateDeckDto {
    private String name;

    private String description;

    private Set<Integer> cardIds;
}
