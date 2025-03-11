package edm.model.entity;

import edm.statemachine.state.OrderState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String subject;

    String author;

    List<String> performerList;

    LocalDate deadline;

    boolean controlSign;

    boolean executionSign;

    @Enumerated(EnumType.STRING)
    OrderState status;

}
