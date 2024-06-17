package edm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "divisions")
public class Division {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, max = 50, message = "Name must be 5-50 characters long")
    private String name;

    @NotBlank(message = "Contact details is required")
    private String contactDetails;

    @NotBlank(message = "Head is required")
    private String head;

}
