package vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.domains.vocab.Topic;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.TopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.TopicDetailDto;

@Component
public class TopicMapper {

    @Lazy
    private WordMapper wordMapper;

    public TopicDto toDto(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .name(topic.getName())
                .build();
    }

    public TopicDetailDto toDetailDto(Topic topic) {
        return TopicDetailDto.builder()
                .id(topic.getId())
                .name(topic.getName())
                .coverImage(topic.getCoverImage())
                .description(topic.getDescription())
                .words(topic.getWords().stream().map(w -> wordMapper.toDto(w)).toList())
                .build();
    }
}
