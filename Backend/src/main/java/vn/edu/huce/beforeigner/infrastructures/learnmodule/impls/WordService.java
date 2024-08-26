package vn.edu.huce.beforeigner.infrastructures.learnmodule.impls;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.Word;
import vn.edu.huce.beforeigner.entities.learn.WordType;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.bussiness.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.mappers.business.WordMapper;
import vn.edu.huce.beforeigner.repositories.CategoryRepo;
import vn.edu.huce.beforeigner.repositories.WordRepo;

@Service
@AllArgsConstructor
public class WordService implements IWordService {

    private WordRepo wordRepo;

    private CategoryRepo categoryRepo;

    private WordMapper wordMapper;

    @Override
    public List<WordDto> getAll(String keyword, Integer categoryId, WordType wordType) {
        return wordRepo.findAll()
            .stream()
            .filter(w -> (StringUtils.hasText(keyword) && w.getValue().contains(keyword))
                || (categoryId > 0 && w.getCategories().stream().allMatch(c -> c.getId().equals(categoryId))))
            .map(w -> wordMapper.toDto(w))
            .toList();
    }

    @Override
    public WordDetailDto getById(Integer id) {
        return wordMapper.toDetail(wordRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.WORD_NOT_FOUND)));
    }

    @Override
    public void addNew(CreateWordDto createWordDto) {
        Word word = new Word();
        word.setValue(createWordDto.getValue());
        word.setAudioFile(createWordDto.getAudioFile());
        word.setPhonetic(createWordDto.getPhonetic());
        word.setWordType(createWordDto.getWordType());
        word.setWordExamples(wordMapper.toEntity(createWordDto.getWordExamples()));
        word.setCategories(categoryRepo.findByIdIn(createWordDto.getCategoryIds()));
        wordRepo.save(word);
    }

    @Override
    public WordDetailDto update(Integer wordId, UpdateWordDto updateWordDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        wordRepo.deleteById(id);
    }
    
}
