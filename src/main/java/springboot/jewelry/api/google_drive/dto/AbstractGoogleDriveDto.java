package springboot.jewelry.api.google_drive.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractGoogleDriveDto implements Serializable {
    private String id;
    private String name;
    private String link;
}
