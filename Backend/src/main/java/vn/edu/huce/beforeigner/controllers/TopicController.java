package vn.edu.huce.beforeigner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.annotations.IsAdmin;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.ITopicService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.TopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.creatation.CreateTopicDto;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail.TopicDetailDto;

@AllArgsConstructor
@RestController
@RequestMapping("api/topic")
public class TopicController {
    
    private ITopicService topicService;

    @GetMapping
    public List<TopicDto> getAll() {
        return topicService.getAll();
    }

    @GetMapping("{id}")
    public TopicDetailDto getById(@PathVariable Integer id) {
        return topicService.getById(id);
    }

    @IsAdmin
    @PostMapping
    public void addNew(CreateTopicDto createTopicDto) {
        topicService.addNew(createTopicDto);
    }
    
}
