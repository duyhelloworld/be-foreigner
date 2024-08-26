package vn.edu.huce.beforeigner.infrastructures.learnmodule.impls;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts.ISoundService;

@Service
public class SoundService implements ISoundService {

    @Override
    public Stream<?> getSound(String word) {
        throw new UnsupportedOperationException("Unimplemented method 'getSound'");
    }
    
}
