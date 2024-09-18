package vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String save(byte[] file, String fileName);
    
    String save(MultipartFile file);
    
    void save(List<MultipartFile> files, String folderName);
    
    List<String> getUrls(String folderName);
    
    void delete(String folderName);

    void deleteOne(String url);
}
