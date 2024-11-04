package vn.edu.huce.beforeigner.infrastructures.storagemodule.impls;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;
import vn.edu.huce.beforeigner.domains.storage.repo.CloudFileRepository;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts.ISoundService;

@Service
@RequiredArgsConstructor
public class SoundService implements ISoundService {

    private final CloudFileRepository cloudFileRepo;

    @Override
    public CloudFile getAndSaveWordAudio(String word) {
        CloudFile cloudFile = new CloudFile();
        cloudFile.setFilename(word + ".mp3");
        cloudFile.setUrl("https://d1qx7pbj0dvboc.cloudfront.net/" + word + ".mp3");
        cloudFile.setType(CloudFileType.WORD_AUDIO);
        return cloudFileRepo.save(cloudFile);
    }
    
}
