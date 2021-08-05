package springboot.jewelry.api.gdrive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GDriveFileDto extends AbstractGDriveDto {

    private String size;
    private String thumbnailLink;
    private boolean shared;
}
