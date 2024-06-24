package edm.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private Long id;

    @NotNull(message = "Firstname is required")
    @Size(min = 5, max = 50, message = "Firstname must be 5-50 characters long")
    private String firstname;

    @NotNull(message = "Lastname is required")
    @Size(min = 5, max = 50, message = "Lastname must be 5-50 characters long")
    private String lastname;

    private String middlename;

}
