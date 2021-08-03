package springboot.jewelry.api.google_drive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleDriveFileDto extends AbstractGoogleDriveDto {

    private String size;
    private String thumbnailLink;
    private boolean shared;
}
