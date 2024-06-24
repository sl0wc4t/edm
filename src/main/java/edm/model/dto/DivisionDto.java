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
public class DivisionDto {

    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 5, max = 50, message = "Name must be 5-50 characters long")
    private String name;

    @NotNull(message = "Contact details is required")
    private String contactDetails;

    @NotNull(message = "Head is required")
    private String head;

}
