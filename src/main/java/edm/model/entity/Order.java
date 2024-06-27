package edm.model.entity;

import edm.statemachine.state.OrderState;
import jakarta.persistence.*;
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
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String subject;

    private String author;

    private List<String> performerList;

    private LocalDate deadline;

    private boolean controlSign;

    private boolean executionSign;

    @Enumerated(EnumType.STRING)
    private OrderState status;

}
