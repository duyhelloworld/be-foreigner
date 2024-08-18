package vn.edu.huce.beforeigner.services.abstracts.business;

import java.util.List;

import vn.edu.huce.beforeigner.dtos.bussiness.CardDto;
import vn.edu.huce.beforeigner.dtos.bussiness.creatation.CreateCardDto;
import vn.edu.huce.beforeigner.dtos.bussiness.detail.CardDetailDto;
import vn.edu.huce.beforeigner.dtos.bussiness.updatation.UpdateCardDto;

public interface ICardService {
    
    List<CardDto> getAll(Integer categoryId);

    CardDetailDto getById(Integer id);

    void addNew(CreateCardDto createCardDto);

    CardDetailDto update(Integer cardId, UpdateCardDto updateCardDto);

    void delete(Integer id);
}
