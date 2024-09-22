package vn.edu.huce.beforeigner.infrastructures.vocabmodule.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.domains.vocab.Topic;
import vn.edu.huce.beforeigner.domains.vocab.repo.TopicRepository;
import vn.edu.huce.beforeigner.domains.vocab.repo.WordRepository;
import vn.edu.huce.beforeigner.exceptions.AppException;
import vn.edu.huce.beforeigner.exceptions.ResponseCode;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.ITopicService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.TopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateTopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.TopicDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateTopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers.TopicMapper;

@Service
@AllArgsConstructor
@Transactional
public class TopicService implements ITopicService {

    private TopicRepository topicRepo;

    private WordRepository wordRepo;

    private TopicMapper topicMapper;

    @Override
    public List<TopicDto> getAll() {
        return topicRepo.findAll().stream().map(d -> topicMapper.toDto(d)).toList();
    }

    @Override
    public TopicDetailDto getById(Integer id) {
        return topicMapper.toDetailDto(topicRepo.findById(id).orElseThrow(() -> new AppException(ResponseCode.TOPIC_NOT_FOUND)));
    }

    @Override
    public void addNew(CreateTopicDto createTopicDto) {
        Topic topic = new Topic();
        topic.setWords(wordRepo.findByIdIn(createTopicDto.getWordIds()));
        topicRepo.save(topic);
    }

    @Override
    public TopicDetailDto update(Integer id, UpdateTopicDto updateTopicDto) {
        return topicMapper.toDetailDto(null);
    }

    @Override
    public void delete(Integer id) {
        topicRepo.deleteById(id);
    }
    
}
