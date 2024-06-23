package edm.service;

import edm.entity.Order;
import edm.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private OrderRepository orderRepository;

    public Iterable<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Order found with id = " + id));
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Order patch) {
        Order order = getById(patch.getId());
        order.setSubject(patch.getSubject());
        order.setAuthor(patch.getAuthor());
        order.setPerformerList(patch.getPerformerList());
        order.setDeadline(patch.getDeadline());
        order.setControlSign(patch.isControlSign());
        order.setExecutionSign(patch.isExecutionSign());
        return save(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

}
