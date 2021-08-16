package springboot.jewelry.api.gdrive.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.jewelry.api.commondata.model.ResponseHandler;
import springboot.jewelry.api.gdrive.dto.Test123;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFileManager;
import springboot.jewelry.api.gdrive.manager.itf.GDriveFolderManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/gdrive")
public class GDriveController {

    private GDriveFileManager gDriveFileManager;

    private GDriveFolderManager gDriveFolderService;

    @PostMapping(value = "/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> uploadFile(@RequestParam(value = "files[]") MultipartFile[] files) {
        //String folderId = gDriveFolderService.findIdByName("", "Sản phẩm/Sản phẩm 01");
        //System.out.println("ten: " + test123.getName());
        System.out.println("file: " + files[0] + files[1]);
        //System.out.println("folder id: " + folderId);
        //List<String> fileId = gDriveFileManager.uploadFile(folderId, test123.getFiles());

        return ResponseHandler.getResponse("123", HttpStatus.OK);
    }
}
