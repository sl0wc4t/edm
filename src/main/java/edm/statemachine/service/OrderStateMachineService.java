package edm.statemachine.service;

import edm.statemachine.event.OrderEvent;
import edm.statemachine.state.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineException;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Data
@AllArgsConstructor
public class OrderStateMachineService {

    private StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;

    private StateMachinePersister<OrderState, OrderEvent, Long> stateMachinePersister;

    public void create(Long id) {
        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            stateMachinePersister.persist(stateMachine, id);
        } catch (Exception e) {
            log.error("Error while creating state machine with id = {}", id, e);
            throw new StateMachineException("Error on state machine creating", e);
        }
    }

    public void next(Long id, OrderEvent event) {
        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            stateMachinePersister.restore(stateMachine, id);
            stateMachine.sendEvent(event);
            stateMachinePersister.persist(stateMachine, id);
        } catch (Exception e) {
            log.error("Error during event invocation, id = {}, event = {}", id, event);
            throw new StateMachineException("Error during event invocation", e);
        }
    }

}
