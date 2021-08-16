package springboot.jewelry.api.gdrive.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class Test123 {

    private String name;

    private List<MultipartFile> files;
}
