package vn.edu.huce.beforeigner.infrastructures.vocabmodule.impls;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.edu.huce.beforeigner.commons.AppObjectMapper;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts.IExampleService;
import vn.edu.huce.beforeigner.infrastructures.vocabmodule.dtos.ExampleEncodeDto;

@Service
@AllArgsConstructor
public class ExampleService implements IExampleService {

    private AppObjectMapper appObjectMapper;

    @Override
    public String encode(String sentense, String... bolds) {
        return appObjectMapper.toJson(ExampleEncodeDto.builder().text(sentense).bolds(bolds).build());
    }

}
