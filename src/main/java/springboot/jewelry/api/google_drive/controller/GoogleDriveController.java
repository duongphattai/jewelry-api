package springboot.jewelry.api.google_drive.controller;

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
import springboot.jewelry.api.google_drive.service.GoogleDriveService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/gdrive")
public class GoogleDriveController {

    private GoogleDriveService googleDriveService;

    @PostMapping(value = "/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> uploadFile(@RequestParam("fileUpload[]") List<MultipartFile> fileDatas) {
        List<String> fileId = googleDriveService.uploadFile(fileDatas);

        return ResponseHandler.getResponse(fileId, HttpStatus.OK);
    }
}
