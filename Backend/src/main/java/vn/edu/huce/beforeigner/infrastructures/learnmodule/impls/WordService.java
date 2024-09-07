package vn.edu.huce.beforeigner.infrastructures.learnmodule.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.entities.learn.Word;
import vn.edu.huce.beforeigner.entities.learn.WordType;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.dtos.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.mappers.ExampleMapper;
import vn.edu.huce.beforeigner.infrastructures.learnmodule.mappers.WordMapper;
import vn.edu.huce.beforeigner.repositories.WordRepository;
import vn.edu.huce.beforeigner.utils.NumberUtils;

@Service
@AllArgsConstructor
public class WordService implements IWordService {

    private WordRepository wordRepo;

    private WordMapper wordMapper;

    private ExampleMapper exampleMapper;

    @Override
    @Transactional
    public List<WordDto> getAll(int deckId, WordType wordType) {
        return wordRepo.findAll()
                .stream()
                .filter(w -> (NumberUtils.notNullAndGreaterThanZero(deckId) 
                    && w.getCard().getDeck().getId() == deckId))
                .map(w -> wordMapper.toDto(w))
                .toList();
    }

    @Override
    @Transactional
    public WordDetailDto getById(int id) {
        return wordMapper
                .toDetail(wordRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.WORD_NOT_FOUND)));
    }

    @Override
    @Transactional
    public void addNew(CreateWordDto createWordDto) {
        Word word = new Word();
        word.setValue(createWordDto.getValue());
        word.setAudioFile(createWordDto.getAudioFile());
        word.setPhonetic(createWordDto.getPhonetic());
        word.setWordType(createWordDto.getWordType());
        word.setExamples(exampleMapper.toEntity(createWordDto.getExamples()));
        wordRepo.save(word);
    }

    @Override
    public WordDetailDto update(int wordId, UpdateWordDto updateWordDto) {
        return null;
    }

    @Override
    public void delete(int id) {
        wordRepo.deleteById(id);
    }

}
