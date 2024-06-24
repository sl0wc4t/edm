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
public class OrganizationDto {

    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 5, max = 50, message = "Name must be 5-50 characters long")
    private String name;

    @NotNull(message = "Physical address is required")
    @Size(min = 5, max = 50, message = "Physical address must be 5-50 characters long")
    private String physicalAddress;

    @NotNull(message = "Legal address is required")
    @Size(min = 5, max = 50, message = "Legal address must be 5-50 characters long")
    private String legalAddress;

    @NotNull(message = "Head is required")
    @Size(min = 5, max = 50, message = "Head must be 5-50 characters long")
    private String head;

}
