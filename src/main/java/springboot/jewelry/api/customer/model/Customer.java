package springboot.jewelry.api.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.customer.util.CustomerStatus;
import springboot.jewelry.api.role.model.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "jewelry_customer")
public class Customer extends AbstractEntity {

    @NotBlank(message = "{user.username.not-blank}")
    @Size(min = 3, max = 50, message = "{user.username.size}")
    @Column(unique = true, name = "username")
    private String username;

    @NotBlank
    private String password;

//    private String fullName;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private CustomerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private Role role;

}
