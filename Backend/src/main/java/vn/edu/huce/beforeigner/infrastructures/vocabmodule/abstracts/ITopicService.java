package vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts;

import java.util.List;

import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.TopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateTopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.TopicDetailDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.updatation.UpdateTopicDto;

public interface ITopicService {

    List<TopicDto> getAll();

    TopicDetailDto getById(Integer id);

    void addNew(CreateTopicDto createTopicDto);

    TopicDetailDto update(Integer id, UpdateTopicDto updateTopicDto);

    void delete(Integer id);
}
