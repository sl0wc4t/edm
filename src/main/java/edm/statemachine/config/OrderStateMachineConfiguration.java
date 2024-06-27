package edm.statemachine.config;

import edm.statemachine.event.OrderEvent;
import edm.statemachine.state.OrderState;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

import static edm.statemachine.event.OrderEvent.*;
import static edm.statemachine.state.OrderState.*;

@Log4j2
@Configuration
@EnableStateMachineFactory
public class OrderStateMachineConfiguration extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(final StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(PREPARATION)
                .end(ACCEPTANCE)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(PREPARATION)
                .target(EXECUTION)
                .event(EXECUTE)

                .and()
                .withExternal()
                .source(EXECUTION)
                .target(VALIDATION)
                .event(VALIDATE)

                .and()
                .withExternal()
                .source(VALIDATION)
                .target(CORRECTION)
                .event(REJECT)

                .and()
                .withExternal()
                .source(CORRECTION)
                .target(EXECUTION)
                .event(EXECUTE)

                .and()
                .withExternal()
                .source(VALIDATION)
                .target(ACCEPTANCE)
                .event(APPROVE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Bean
    public StateMachinePersister<OrderState, OrderEvent, Long> stateMachinePersister(OrderStateMachinePersist orderStateMachinePersist) {
        return new DefaultStateMachinePersister<>(orderStateMachinePersist);
    }

}
