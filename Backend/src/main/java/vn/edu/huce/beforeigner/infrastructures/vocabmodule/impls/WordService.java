package vn.edu.huce.beforeigner.infrastructures.vocabmodule.impls;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.vocab.Example;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.CloudFileType;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.IWordService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;
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
        var audioResp = cloudFileService.save(createWordDto.getAudio(), CloudFileType.WORD_AUDIO);
        word.setAudioUrl(audioResp.getUrl());
        word.setAudioFilename(audioResp.getFilename());
        word.setAudioPublicId(audioResp.getPublicId());

        var imageResp = cloudFileService.save(createWordDto.getAudio(), CloudFileType.WORD_IMAGE);
        word.setImageUrl(imageResp.getUrl());
        word.setImageFilename(imageResp.getFilename());
        word.setImagePublicId(imageResp.getPublicId());

        word.setExamples(createWordDto.getExamples().stream().map(ce -> {
            Example ex = new Example();
            ex.setMean(ce.getMean());
            ex.setSentense(ce.getSentense());
            return ex;
        }).collect(Collectors.toSet()));
        word.setPhonetic(createWordDto.getPhonetic());
        word.setValue(createWordDto.getValue());
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
