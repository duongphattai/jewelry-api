package springboot.jewelry.api.gdrive.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.gdrive.service.GDriveFileService;
import springboot.jewelry.api.gdrive.service.GDriveFolderService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/gdrive")
public class GDriveController {

    private GDriveFileService gDriveFileService;

    private GDriveFolderService gDriveFolderService;

    @PostMapping(value = "/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> uploadFile(@RequestParam("fileUpload[]") List<MultipartFile> fileDatas) {
        String folderId = gDriveFolderService.findIdByName("Sản phẩm/Sản phẩm 01");
        System.out.println("folder id: " + folderId);
        List<String> fileId = gDriveFileService.uploadFile(fileDatas, folderId);

        return ResponseHandler.getResponse(fileId, HttpStatus.OK);
    }
}
