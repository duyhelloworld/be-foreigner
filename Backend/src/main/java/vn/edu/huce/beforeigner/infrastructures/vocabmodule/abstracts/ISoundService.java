package vn.edu.huce.beforeigner.infrastructures.vocabmodule.abstracts;

import java.util.stream.Stream;

public interface ISoundService {
    
    Stream<?> getSound(String word);

}