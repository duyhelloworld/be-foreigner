package vn.edu.huce.beforeigner.infrastructures.filemodule.impls;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.huce.beforeigner.infrastructures.filemodule.abstracts.IFileService;

@Service
public class FileService implements IFileService {

    @Override
    public String save(byte[] file, String fileName) {
        return "";
    }

    @Override
    public String save(MultipartFile file) {
        return "";
    }

    @Override
    public void save(List<MultipartFile> files, String folderName) {
    }

    @Override
    public List<String> getUrls(String folderName) {
        return List.of("");
    }

    @Override
    public void delete(String folderName) {
    }

    @Override
    public void deleteOne(String url) {

    }
    
}
