package springboot.jewelry.api.google_drive.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoogleDriveService {
    List<String> uploadFile(List<MultipartFile> fileDatas);
}
