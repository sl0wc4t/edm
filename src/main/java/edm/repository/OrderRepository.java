package edm.repository;

import edm.model.entity.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    static Specification<Order> defaultCriteria() {
        return (order, cq, cb) -> cb.isNotNull(order.get("id"));
    }

    static Specification<Order> hasId(Long id) {
        return (order, cq, cb) -> cb.equal(order.get("id"), id);
    }

    static Specification<Order> subjectStartsWith(String subjectPart) {
        return (order, cq, cb) -> cb.like(order.get("subject"), subjectPart + "%");
    }

    static Specification<Order> authorStartsWith(String authorPart) {
        return (order, cq, cb) -> cb.like(order.get("author"), authorPart + "%");
    }

    static Specification<Order> performerListContains(String performer) {
        return (order, cq, cb) -> cb.like(order.get("performerList"), "%" + performer + "%");
    }

    static Specification<Order> hasDeadline(LocalDate deadline) {
        return (order, cq, cb) -> cb.equal(order.get("deadline"), deadline);
    }

    static Specification<Order> hasControlSign(boolean controlSign) {
        return (order, cq, cb) -> cb.equal(order.get("controlSign"), controlSign);
    }

    static Specification<Order> hasExecutionSign(boolean executionSign) {
        return (order, cq, cb) -> cb.equal(order.get("executionSign"), executionSign);
    }

}
