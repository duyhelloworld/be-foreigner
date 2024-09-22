package vn.edu.huce.beforeigner.infrastructures.vocabmodule.impls;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.domains.vocab.Topic;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.repo.TopicRepository;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.IImageService;
import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.ISoundService;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.ImageType;
import vn.edu.huce.beforeigner.infrastructures.filemodule.dtos.UploadResponse;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateExampleDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers.WordMapper;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@Service
@RequiredArgsConstructor
public class WordService implements IWordService {

    private final WordRepository wordRepo;

    private final TopicRepository topicRepo;

    private final WordMapper wordMapper;

    private final IImageService imageService;

    private final ISoundService soundService;

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
        word.setAudio(soundService.getWordAudio(createWordDto.getValue()));
        if (createWordDto.getImage() != null) {
            UploadResponse response = imageService.save(createWordDto.getImage(), ImageType.WORD_IMAGE);
            word.setImage(response.getFileUrl());
            word.setPublicId(response.getPublicId());
        }
        Set<Topic> topics = topicRepo.findAllByIdIn(createWordDto.getTopicIds());
        word.setTopics(topics);
        
        Set<Example> examples = new HashSet<>();
        for (CreateExampleDto createExampleDto : createWordDto.getCreateExamples()) {
            examples.add(new Example(createExampleDto.getSentense(), createExampleDto.getMean()));
        }
        word.setExamples(examples);
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
