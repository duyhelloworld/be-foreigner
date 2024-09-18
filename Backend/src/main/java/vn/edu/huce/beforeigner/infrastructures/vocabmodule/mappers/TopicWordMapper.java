package vn.edu.huce.beforeigner.infrastructures.vocabmodule.mappers;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TopicWordMapper {
    
    public TopicMapper topicMapper;

    public WordMapper wordMapper;
}
