package springboot.jewelry.api.gdrive.manager.itf;

import java.util.List;

public interface GDriveFolderManager {

    String findIdByName(String parentId, String name);

    String create(String parentId, String name);

    void deleteFolder(String folderId);
}
