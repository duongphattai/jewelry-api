package springboot.jewelry.api.gdrive.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GDriveFileService {

    List<String> uploadFile(List<MultipartFile> fileDatas, String folderName);
}
