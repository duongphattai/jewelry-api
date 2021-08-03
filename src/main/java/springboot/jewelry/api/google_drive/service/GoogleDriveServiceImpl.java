package springboot.jewelry.api.google_drive.service;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.model.File;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springboot.jewelry.api.google_drive.config.GoogleDriveConfig;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
public class GoogleDriveServiceImpl implements GoogleDriveService {

    private GoogleDriveConfig googleDriveConfig;

    @Override
    public List<String> uploadFile(List<MultipartFile> fileDatas) {
        List<String> idList = new LinkedList<>();
        try {
            for(MultipartFile fileData : fileDatas) {
                if(fileData != null) {
                    File fileMetadata = new File();
                    fileMetadata.setParents(Collections.singletonList("1OV955yp6ouRVyfnSwUIj_t-P6uEv3chz"));
                    fileMetadata.setName(fileData.getOriginalFilename());
                    File uploadFile = googleDriveConfig.getInstance()
                            .files()
                            .create(fileMetadata,
                                    new InputStreamContent(fileData.getContentType(), new ByteArrayInputStream(fileData.getBytes()))
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
