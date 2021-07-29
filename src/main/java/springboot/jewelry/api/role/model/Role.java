package springboot.jewelry.api.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import springboot.jewelry.api.commondata.model.AbstractEntity;
import springboot.jewelry.api.customer.model.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "jewelry_role")
public class Role extends AbstractEntity {

    @NotBlank(message = "{role.name.not-blank}")
    @Size(min = 4, max = 50, message = "{role.name.size}")
    @Column(unique = true, name = "role_name")
    private String roleName;

    @NotBlank(message = "{role.description.not-blank}")
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();
}
