package vn.edu.huce.beforeigner.infrastructures.learnmodule.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import vn.edu.huce.beforeigner.entities.learn.Example;

@Component
public class ExampleMapper {
    
    public List<String> toListString(Collection<Example> examples) {
        return examples.stream().map(Example::getSentense).toList();
    }

    public List<Example> toEntity(List<String> sentenses) {
        List<Example> examples = new ArrayList<>();
        for (String sentense : sentenses) {
            examples.add(new Example(sentense));
        }
        return examples;
    }
    
}
