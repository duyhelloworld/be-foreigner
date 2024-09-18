package vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.detail;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.WordDto;

@Builder
@ToString
@EqualsAndHashCode
public class TopicDetailDto {

    public Integer id;

    public String name;

    public String description;

    public String coverImage;

    public List<WordDto> words;
}
