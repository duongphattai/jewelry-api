package springboot.jewelry.api.gdrive.manager;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFolderManager;
import springboot.jewelry.api.gdrive.util.GDriveUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
public class GDriveFolderManagerImpl implements GDriveFolderManager {

    private GDriveUtils gDriveUtils;

    @Override
    public String findIdByName(String parentId, String name) {
        String pageToken = null;
        FileList files = null;

        try {
            Drive driveInstance = gDriveUtils.getInstance();

            do {
                String query = " mimeType = 'application/vnd.google-apps.folder' ";
                if (parentId == null) {
                    query = query + " and 'root' in parents";
                } else {
                    query = query + " and '" + parentId + "' in parents";
                }

                files = driveInstance.files().list().setQ(query)
                        .setSpaces("drive")
                        .setFields("nextPageToken, files(id, name)")
                        .setPageToken(pageToken)
                        .execute();

                for (File file : files.getFiles()) {
                    if (file.getName().equalsIgnoreCase(name)) {
                        return file.getId();
                    }
                }
                pageToken = files.getNextPageToken();
            } while (pageToken != null);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String create(String parentId, String name) {
        File fileMetadata = new File();
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        fileMetadata.setName(name);
        fileMetadata.setParents(Collections.singletonList(parentId));

        try {
            return gDriveUtils.getInstance().files().create(fileMetadata)
                    .setFields("id")
                    .execute()
                    .getId();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        return null;
    }


}
