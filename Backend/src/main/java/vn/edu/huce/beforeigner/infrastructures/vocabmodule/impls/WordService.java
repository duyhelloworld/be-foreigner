package vn.edu.huce.beforeigner.infrastructures.vocabmodule.impls;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.domains.vocab.Topic;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.repo.TopicRepository;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.enums.ResponseCode;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.IFileService;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingRequest;
import vn.edu.huce.beforeigner.infrastructures.paging.PagingResult;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateExampleDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers.WordMapper;

@Service
@AllArgsConstructor
public class WordService implements IWordService {

    private WordRepository wordRepo;

    private IFileService fileService;

    private TopicRepository topicRepo;

    private WordMapper wordMapper;

    @Override
    public PagingResult<WordDto> getAll(PagingRequest pagingRequest, Integer topicId) {
        return PagingResult.of(
            wordRepo.findByTopicId(topicId, pagingRequest.pageable()),
            w -> wordMapper.toDto(w));
    }

    @Override
    public WordDetailDto getById(Integer id) {
        return wordMapper.toDetailDto(wordRepo.findById(id).orElse(null));
    }

    @Override
    public void addNew(CreateWordDto createWordDto) {
        
        Word word = new Word();

        if (createWordDto.getAudio() != null) {
            String audio = fileService.save(createWordDto.getAudio());
            word.setAudio(audio);
        }
        if (createWordDto.getImage() != null) {
            String image = fileService.save(createWordDto.getImage());
            word.setImage(image);
        }
        Set<Topic> topics = topicRepo.findAllByIdIn(createWordDto.getTopicIds());
        word.setTopics(topics);
        
        Set<Example> examples = new HashSet<>();
        for (CreateExampleDto createExampleDto : createWordDto.getCreateExamples()) {
            examples.add(new Example(createExampleDto.getSentense(), createExampleDto.getMean()));
        }
        word.setExamples(examples);

        word.setMean(createWordDto.getMean());
        word.setPhonetic(createWordDto.getPhonetic());
        word.setValue(createWordDto.getValue());
        word.setWordType(createWordDto.getWordType());
        wordRepo.save(word);
    }

    @Override
    public WordDetailDto update(Integer id, UpdateWordDto updateWordDto) {
        Word word = wordRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.WORD_NOT_FOUND));
        return wordMapper.toDetailDto(word);
    }

    @Override
    public void delete(Integer id) {
        wordRepo.deleteById(id);
    }

}
