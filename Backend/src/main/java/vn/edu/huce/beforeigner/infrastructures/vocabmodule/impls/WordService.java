package vn.edu.huce.beforeigner.infrastructures.vocabmodule.impls;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;
import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateExampleDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.WordDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateWordDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers.WordMapper;
import vn.edu.huce.beforeigner.utils.NumberUtils;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@Service
@RequiredArgsConstructor
public class WordService implements IWordService {

    private final WordRepository wordRepo;

    private final WordMapper wordMapper;

    private final ICloudFileService imageService;

    private final ICloudFileService cloudFileService;

    @Override
    public PagingResult<WordDto> getAll(PagingRequest pagingRequest) {
        return PagingResult.of(
            wordRepo.findAll(pagingRequest.pageable()),
            w -> wordMapper.toDto(w));
    }

    @Override
    public WordDetailDto getDetailById(Integer id) {
        return wordMapper.toDetailDto(wordRepo.findById(id).orElse(null));
    }

    @Override
    public void addNew(CreateWordDto createWordDto) {
        
        Word word = new Word();
        word.setAudio(cloudFileService.saveAndGet(CloudFileType.VOICE_AUDIO, createWordDto.getValue()));

        if (createWordDto.getImage() != null) {
            CloudFile response = imageService.save(createWordDto.getImage(), CloudFileType.WORD_IMAGE);
            word.setImage(response);
        }
        
        Set<Example> examples = new HashSet<>();
        for (CreateExampleDto createExampleDto : createWordDto.getExamples()) {
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

    @Override
    public WordDto getTodayWord(User user) {
        // Tạm thời user kệ, cứ lấy random word thôi
        int total = (int) wordRepo.count();
        int randomPos = NumberUtils.randomNumber(1, total);
        Page<Word> page = wordRepo.findAll(Pageable.ofSize(1).withPage(randomPos));
        if (page.hasContent()) {
            return wordMapper.toDto(page.getContent().get(0));
        }
        return null;
    }
}
