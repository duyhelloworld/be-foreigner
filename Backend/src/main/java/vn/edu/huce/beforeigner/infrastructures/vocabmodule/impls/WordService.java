package vn.edu.huce.beforeigner.infrastructures.vocabmodule.impls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.core.Account;
import vn.edu.huce.beforeigner.domains.vocab.Word;
import vn.edu.huce.beforeigner.domains.vocab.repo.SentenseRepository;
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

    private final SentenseRepository sentenseRepo;

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

        word.setPhonetic(createWordDto.getPhonetic());
        word.setValue(createWordDto.getValue());
        word.setMean(createWordDto.getMean());
        
        var audioResp = cloudFileService.save(createWordDto.getAudio(), CloudFileType.WORD_AUDIO);
        word.setAudioUrl(audioResp.getUrl());
        word.setAudioPublicId(audioResp.getPublicId());

        var imageResp = cloudFileService.save(createWordDto.getAudio(), CloudFileType.WORD_IMAGE);
        word.setImageUrl(imageResp.getUrl());
        word.setImagePublicId(imageResp.getPublicId());

        if (createWordDto.getSentenseIds() != null) {
            var sentenses = sentenseRepo.findByIdIn(createWordDto.getSentenseIds());
            word.setSentenses(sentenses);
        }
        wordRepo.save(word);
    }

    @Override
    public WordDetailDto update(Integer id, UpdateWordDto updateWordDto) {
        Word word = wordRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.WORD_NOT_FOUND));
        word.setMean(updateWordDto.getMean());
        word.setValue(updateWordDto.getValue());
        word.setPhonetic(updateWordDto.getPhonetic());

        var audioResp = cloudFileService.save(updateWordDto.getAudio(), CloudFileType.WORD_AUDIO);
        word.setAudioUrl(audioResp.getUrl());
        word.setAudioPublicId(audioResp.getPublicId());

        var imageResp = cloudFileService.save(updateWordDto.getAudio(), CloudFileType.WORD_IMAGE);
        word.setImageUrl(imageResp.getUrl());
        word.setImagePublicId(imageResp.getPublicId());

        wordRepo.save(word);
        return wordMapper.toDetailDto(word);
    }

    @Override
    public void delete(Integer id) {
        wordRepo.deleteById(id);
    }

    @Override
    public WordDto getTodayWord(Account user) {
        // Tạm thời user kệ, cứ lấy random word thôi
        int total = (int) wordRepo.count();
        int randomPos = NumberUtils.randomNumber(1, total);
        Page<Word> page = wordRepo.findAll(
            Pageable.ofSize(1).withPage(randomPos));
        if (page.hasContent()) {
            return wordMapper.toDto(page.getContent().get(0));
        }
        throw new AppException(ResponseCode.WORD_NOT_FOUND);
    }
}
