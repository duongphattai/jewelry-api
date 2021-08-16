package springboot.jewelry.api.gdrive.manager;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.model.File;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFileManager;
import springboot.jewelry.api.gdrive.util.GDriveUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
public class GDriveFileManagerImpl implements GDriveFileManager {

    private GDriveUtils gDriveConfig;

    @Override
    public List<String> uploadFile(String folderName, List<MultipartFile> fileDatas) {
        List<String> idList = new LinkedList<>();
        try {
            for(MultipartFile fileData : fileDatas) {
                if(fileData != null) {
                    File fileMetadata = new File();
                    fileMetadata.setParents(Collections.singletonList(folderName));
                    fileMetadata.setName(fileData.getOriginalFilename());
                    File uploadFile = gDriveConfig.getInstance()
                            .files()
                            .create(fileMetadata,
                                    new InputStreamContent(fileData.getContentType(),
                                    new ByteArrayInputStream(fileData.getBytes()))
                            )
                            .setFields("id")
                            .execute();

                    idList.add(uploadFile.getId());
                }
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        return idList;
    }
}
