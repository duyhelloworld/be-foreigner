package vn.edu.huce.beforeigner.dtos.bussiness.creatation;

import lombok.Data;

@Data
public class CreateCardDto {
    
    private String imageUrl;

    private Integer deckId;

    private Integer wordId;

    private CreateWordDto wordDto;
}
