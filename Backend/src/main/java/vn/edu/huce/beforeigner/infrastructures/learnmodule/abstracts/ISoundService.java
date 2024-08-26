package vn.edu.huce.beforeigner.infrastructures.learnmodule.abstracts;

import java.util.stream.Stream;

public interface ISoundService {
    
    Stream<?> getSound(String word);
    
}
