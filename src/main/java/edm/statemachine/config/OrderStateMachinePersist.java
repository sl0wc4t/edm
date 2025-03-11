package edm.statemachine.config;

import edm.repository.OrderRepository;
import edm.statemachine.event.OrderEvent;
import edm.statemachine.state.OrderState;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderStateMachinePersist implements StateMachinePersist<OrderState, OrderEvent, Long> {

    OrderRepository orderRepository;

    @Override
    public void write(StateMachineContext<OrderState, OrderEvent> context, Long id) throws Exception {

        orderRepository.updateStatusById(context.getState(), id);
    }

    @Override
    public StateMachineContext<OrderState, OrderEvent> read(Long id) throws Exception {

        OrderState state = orderRepository.findStatusById(id);

        return new DefaultStateMachineContext<>(state, null, null, null);
    }

}
