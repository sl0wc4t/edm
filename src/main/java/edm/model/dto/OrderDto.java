package edm.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    @NotNull(message = "Subject is required")
    @Size(min = 5, max = 50, message = "Subject must be 5-50 characters long")
    private String subject;

    @NotNull(message = "Author is required")
    @Size(min = 5, max = 50, message = "Author must be 5-50 characters long")
    private String author;

    @NotEmpty(message = "Performer list is required")
    private List<String> performerList;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Deadline is required")
    private LocalDate deadline;

    private boolean controlSign;

    private boolean executionSign;

}
