package springboot.jewelry.api.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.customer.util.CustomerGender;
import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.util.DateUtils;
import springboot.jewelry.api.util.FormatUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;



@Getter
@Setter
@Entity
@Table(name = "jewelry_customer")
public class Customer extends AbstractEntity {

    @NotBlank(message = "{customer.username.not-blank}")
    @Size(min = 3, max = 50, message = "{customer.username.size}")
    @Column(unique = true, name = "username")
    private String username;

    @NotBlank(message = "{customer.password.not-blank}")
    @JsonIgnore
    private String password;

    @NotBlank(message = "{customer.full-name.not-blank}")
    @Size(min = 3, max = 50, message = "{customer.full-name.size}")
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CustomerGender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.BIRTHDAY_FORMAT)
    private LocalDate birthday;

    @NotBlank(message = "{customer.mobile-no.not-blank}")
    @Pattern(regexp = FormatUtils.PHONE_NUMBER_FORMAT, message = "{customer.mobile-no.format}")
    @Column(unique = true)
    private String phoneNumber;

    @Email(message = "{customer.email.format}")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "{customer.address.not-blank}")
    @Size(min = 20, max = 100, message = "{customer.address.size}")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}
