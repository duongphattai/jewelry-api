package springboot.jewelry.api.security.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfile {
    private Long id;
    private String email;
    private String fullName;
    private Boolean active;
}
