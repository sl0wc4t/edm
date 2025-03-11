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
public class OrganizationDto {

    Long id;

    @NotNull(message = "Name is required")
    @Size(min = 5, max = 50, message = "Name must be 5-50 characters long")
    String name;

    @NotNull(message = "Physical address is required")
    @Size(min = 5, max = 50, message = "Physical address must be 5-50 characters long")
    String physicalAddress;

    @NotNull(message = "Legal address is required")
    @Size(min = 5, max = 50, message = "Legal address must be 5-50 characters long")
    String legalAddress;

    @NotNull(message = "Head is required")
    @Size(min = 5, max = 50, message = "Head must be 5-50 characters long")
    String head;

}
