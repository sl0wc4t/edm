package edm.service;

import edm.model.dto.OrderDto;
import edm.model.entity.Order;
import edm.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static edm.repository.OrderRepository.*;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    public Page<OrderDto> getAll(Integer pageNumber, Integer pageSize, String field,
                                 Long id, String subjectPart, String authorPart, String performer, String deadline, String controlSign, String executionSign) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(field));
        Specification<Order> criteria = defaultCriteria();
        if (id != null) {
            criteria = criteria.and(hasId(id));
        }
        if (subjectPart != null) {
            criteria = criteria.and(subjectStartsWith(subjectPart));
        }
        if (authorPart != null) {
            criteria = criteria.and(authorStartsWith(authorPart));
        }
        if (performer != null) {
            criteria = criteria.and(performerListContains(performer));
        }
        if (deadline != null) {
            criteria = criteria.and(hasDeadline(LocalDate.parse(deadline, DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        }
        if (controlSign != null) {
            criteria = criteria.and(hasControlSign(Boolean.parseBoolean(controlSign)));
        }
        if (executionSign != null) {
            criteria = criteria.and(hasExecutionSign(Boolean.parseBoolean(executionSign)));
        }
        return orderRepository.findAll(criteria, pageRequest)
                .map(this::toDto);
    }

    public OrderDto getById(Long id) {
        return toDto(orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Order found with id = " + id)));
    }

    public OrderDto save(OrderDto orderDto) {
        return toDto(orderRepository.save(toEntity(orderDto)));
    }

    public OrderDto update(OrderDto patch) {
        OrderDto orderDto = getById(patch.getId());
        orderDto.setSubject(patch.getSubject());
        orderDto.setAuthor(patch.getAuthor());
        orderDto.setPerformerList(patch.getPerformerList());
        orderDto.setDeadline(patch.getDeadline());
        orderDto.setControlSign(patch.isControlSign());
        orderDto.setExecutionSign(patch.isExecutionSign());
        return save(orderDto);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .subject(order.getSubject())
                .author(order.getAuthor())
                .performerList(order.getPerformerList())
                .deadline(order.getDeadline())
                .controlSign(order.isControlSign())
                .executionSign(order.isExecutionSign())
                .build();
    }

    private Order toEntity(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .subject(orderDto.getSubject())
                .author(orderDto.getAuthor())
                .performerList(orderDto.getPerformerList())
                .deadline(orderDto.getDeadline())
                .controlSign(orderDto.isControlSign())
                .executionSign(orderDto.isExecutionSign())
                .build();
    }

}
