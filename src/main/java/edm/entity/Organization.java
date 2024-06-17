package edm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, max = 50, message = "Name must be 5-50 characters long")
    private String name;

    @NotNull
    @Size(min = 5, max = 50, message = "Physical address must be 5-50 characters long")
    private String physicalAddress;

    @NotNull
    @Size(min = 5, max = 50, message = "Legal address must be 5-50 characters long")
    private String legalAddress;

    @NotNull
    @Size(min = 5, max = 50, message = "Head must be 5-50 characters long")
    private String head;

}
