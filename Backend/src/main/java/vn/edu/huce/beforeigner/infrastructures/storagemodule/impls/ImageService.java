package vn.edu.huce.beforeigner.infrastructures.storagemodule.impls;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.huce.beforeigner.domains.storage.CloudFile;
import vn.edu.huce.beforeigner.domains.storage.CloudFileType;
import vn.edu.huce.beforeigner.domains.storage.repo.CloudFileRepository;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.abstracts.IImageService;
import vn.edu.huce.beforeigner.infrastructures.storagemodule.dtos.UnflashImageDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
   
    private final CloudFileRepository cloudFileRepo;

    private final RestTemplate restTemplate;

    @Override
    public CloudFile getAndSaveWordImage(String keyword) {
        try {
            var response = restTemplate.getForObject("https://api.unsplash.com/search/photos?per_page=1&page=1&&client_id=8z25mu-4OIYz1oxWPZcc-XWu27nh-q-19Rogk38rSzA&query=" + keyword, UnflashImageDto.class)
                .getResults().get(0);
            CloudFile cloudFile = new CloudFile();
            cloudFile.setFilename(response.getSlug());
            cloudFile.setUrl(response.getUrls().getRaw());
            cloudFile.setType(CloudFileType.WORD_IMAGE);
            return cloudFileRepo.save(cloudFile);
        } catch (RestClientException e) {
            log.error("Request image error : {}", e.getMessage());
            return null;
        }
    }
}
