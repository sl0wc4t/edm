package edm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, max = 50, message = "Subject must be 5-50 characters long")
    private String subject;

    @NotNull
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
