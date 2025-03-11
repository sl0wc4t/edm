package edm.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {

    Long id;

    @NotNull(message = "Firstname is required")
    @Size(min = 5, max = 50, message = "Firstname must be 5-50 characters long")
    String firstname;

    @NotNull(message = "Lastname is required")
    @Size(min = 5, max = 50, message = "Lastname must be 5-50 characters long")
    String lastname;

    String middlename;

}
