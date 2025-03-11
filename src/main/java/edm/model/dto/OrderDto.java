package edm.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edm.statemachine.state.OrderState;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    Long id;

    @NotNull(message = "Subject is required")
    @Size(min = 5, max = 50, message = "Subject must be 5-50 characters long")
    String subject;

    @NotNull(message = "Author is required")
    @Size(min = 5, max = 50, message = "Author must be 5-50 characters long")
    String author;

    @NotEmpty(message = "Performer list is required")
    List<String> performerList;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Deadline is required")
    LocalDate deadline;

    boolean controlSign;

    boolean executionSign;

    OrderState status;

}
