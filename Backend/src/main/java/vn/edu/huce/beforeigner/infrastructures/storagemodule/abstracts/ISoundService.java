package vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts;

import vn.edu.huce.beforeigner.domains.storage.CloudFile;

public interface ISoundService {
    
    CloudFile getAndSaveWordAudio(String word);
    
}