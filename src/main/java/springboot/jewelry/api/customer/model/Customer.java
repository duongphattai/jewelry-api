package springboot.jewelry.api.customer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.customer.util.CustomerGender;
import springboot.jewelry.api.role.model.Role;
import springboot.jewelry.api.util.DateUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;



@Getter
@Setter
@Entity
@Table(name = "jewelry_customer")
public class Customer extends AbstractEntity {

    @NotBlank(message = "{Customer.username.NotBlank}")
    @Size(min = 3, max = 50, message = "{Customer.username.Size}")
    @Column(unique = true, name = "username")
    private String username;

    @NotBlank(message = "{Customer.password.NotBlank}")
    @JsonIgnore
    private String password;

    @NotBlank(message = "{Customer.fullName.NotBlank}")
    @Size(min = 3, max = 50, message = "{Customer.fullName.Size}")
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CustomerGender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.BIRTHDAY_FORMAT)
    private LocalDate birthday;

    @NotBlank(message = "{Customer.phoneNumber.NotBlank}")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "{Customer.phoneNumber.Pattern}")
    @Column(unique = true)
    private String phoneNumber;

    @Email(message = "{Customer.email.Email}")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "{Customer.address.NotBlank}")
    @Size(min = 20, max = 100, message = "{Customer.address.Size}")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @NotNull
    private Role role;

}
