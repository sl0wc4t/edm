package edm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, max = 50, message = "Firstname must be 5-50 characters long")
    private String firstname;

    @NotNull
    @Size(min = 5, max = 50, message = "Lastname must be 5-50 characters long")
    private String lastname;

    private String middlename;

}
