package vn.edu.huce.beforeigner.infrastructures.filemodule.impls;

import org.springframework.stereotype.Service;

import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.ISoundService;

@Service
public class SoundService implements ISoundService {

    @Override
    public String getWordAudio(String word) {
        return "https://d1qx7pbj0dvboc.cloudfront.net/" + word + ".mp3";
    }
    
}
