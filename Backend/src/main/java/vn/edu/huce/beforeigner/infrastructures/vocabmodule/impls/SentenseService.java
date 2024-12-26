package vn.edu.huce.beforeigner.infrastructures.vocabmodule.impls;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.vocab.Sentense;
import vn.edu.huce.beforeigner.domains.vocab.repo.SentenseRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.abstracts.ICloudFileService;
import vn.edu.huce.beforeigner.infrastructures.cloudmodule.dtos.CloudFileType;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.ISentenseService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.SentenseDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateSentenseDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.SentenseDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateSentenseDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers.SentenseMapper;
import vn.edu.huce.beforeigner.utils.paging.PagingRequest;
import vn.edu.huce.beforeigner.utils.paging.PagingResult;

@Slf4j
@Service
@RequiredArgsConstructor
public class SentenseService implements ISentenseService {

    private final SentenseRepository sentenseRepo;

    private final SentenseMapper sentenseMapper;

    private final ICloudFileService cloudFileService;

    @Override
    public PagingResult<SentenseDto> getAll(PagingRequest pagingRequest) {
        return PagingResult.of(
                sentenseRepo.findAll(pagingRequest.pageable()),
                sentense -> sentenseMapper.toDto(sentense));
    }

    @Override
    public SentenseDetailDto getDetailById(Integer id) {
        return sentenseMapper.toDetailDto(
                sentenseRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.SENTENSE_NOT_FOUND)));
    }

    @Override
    public void addNew(CreateSentenseDto createSentenseDto) {
        Sentense sentense = new Sentense();

        sentense.setValue(createSentenseDto.getValue());
        sentense.setMean(createSentenseDto.getMean());
        
        var audioResp = cloudFileService.save(createSentenseDto.getAudio(), CloudFileType.SENTENSE_AUDIO);
        sentense.setAudioUrl(audioResp.getUrl());
        sentense.setAudioPublicId(audioResp.getPublicId());
        sentenseRepo.save(sentense);
    }

    @Override
    public SentenseDetailDto update(Integer id, UpdateSentenseDto updateSentenseDto) {
        Sentense sentense =  sentenseRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.SENTENSE_NOT_FOUND));
        sentense.setValue(updateSentenseDto.getValue());
        sentense.setMean(updateSentenseDto.getMean());
        
        var audioResp = cloudFileService.save(updateSentenseDto.getAudio(), CloudFileType.SENTENSE_AUDIO);
        sentense.setAudioUrl(audioResp.getUrl());
        sentense.setAudioPublicId(audioResp.getPublicId());
        sentenseRepo.save(sentense);
        return sentenseMapper.toDetailDto(sentense);
    }

    @Override
    public void delete(Integer id) {
        sentenseRepo.deleteById(id);
    }

}
