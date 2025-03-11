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
public class DivisionDto {

    Long id;

    @NotNull(message = "Name is required")
    @Size(min = 5, max = 50, message = "Name must be 5-50 characters long")
    String name;

    @NotNull(message = "Contact details is required")
    String contactDetails;

    @NotNull(message = "Head is required")
    String head;

}
