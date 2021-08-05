package springboot.jewelry.api.gdrive.service.impl;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.gdrive.config.GDriveConfig;
import springboot.jewelry.api.gdrive.service.GDriveFolderService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
public class GDriveFolderServiceImpl implements GDriveFolderService {

    private GDriveConfig gDriveConfig;

    @Override
    public String findIdByName(String name){
        String[] folderNames = name.split("/");
        List<String> folderIds = new LinkedList<>();
        String currentFolderName = null;

        try {
            Drive driveInstance = gDriveConfig.getInstance();
            for (String folderName : folderNames) {
                String pageToken = null;
                FileList fileList = null;

                do {
                    String query = " mimeType = 'application/vnd.google-apps.folder' ";
                    if (currentFolderName == null) {
                        query = query + " and 'root' in parents";
                    } else {
                        query = query + " and '" + currentFolderName + "' in parents";
                    }

                    fileList = driveInstance.files().list().setQ(query)
                            .setSpaces("drive")
                            .setFields("nextPageToken, files(id, name)")
                            .setPageToken(pageToken)
                            .execute();

                    for (File file : fileList.getFiles()) {
                        if (file.getName().equalsIgnoreCase(folderName)) {
                            currentFolderName = file.getId();
                            folderIds.add(file.getId());
                        }
                    }
                    pageToken = fileList.getNextPageToken();
                } while (pageToken != null);

                if(currentFolderName == null) break;
            }

            if(folderIds.size() == folderNames.length)
                return folderIds.get(folderIds.size() - 1);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
