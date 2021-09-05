package springboot.jewelry.api.gdrive.manager.itf;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface GDriveFileManager {

    List<String> uploadFile(String folderName, List<MultipartFile> fileData);

    void deleteFile(List<String> gDriveId);
}
