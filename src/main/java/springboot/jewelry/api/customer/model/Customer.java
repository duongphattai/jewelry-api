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
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "jewelry_customer")
public class Customer extends AbstractEntity {

    @Email(message = "{customer.email.format}")
    @Column(unique = true, nullable = false)
    private String email;

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

    @NotBlank(message = "{customer.phone-number.not-blank}")
    @Pattern(regexp = "(^((?=(0))[0-9]{10})$)", message = "{customer.phone-number.pattern}")
    @Column(unique = true)
    private String phoneNumber;

    @NotBlank(message = "{customer.address.not-blank}")
    @Size(min = 20, max = 100, message = "{customer.address.size}")
    private String address;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean active;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "jewelry_customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void activate() {
        this.active = true;
    }

}
