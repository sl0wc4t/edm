package edm.controller;

import edm.model.dto.OrderDto;
import edm.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderDto>> getAllOrders(
            @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String performer,
            @RequestParam(required = false) String deadline,
            @RequestParam(required = false) String controlSign,
            @RequestParam(required = false) String executionSign) {
        return ResponseEntity.ok(orderService.getAll(pageNumber, pageSize, sortBy, id, subject, author, performer, deadline, controlSign, executionSign));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> addNewOrder(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.create(orderDto), HttpStatus.CREATED);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto patch) {
        return ResponseEntity.ok(orderService.update(patch));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteById(id);
    }

    @PatchMapping(path = "/{id}/execute")
    public ResponseEntity<OrderDto> execute(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.execute(id));
    }

    @PatchMapping(path = "/{id}/validate")
    public ResponseEntity<OrderDto> validate(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.validate(id));
    }

    @PatchMapping(path = "/{id}/approve")
    public ResponseEntity<OrderDto> approve(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.approve(id));
    }

    @PatchMapping(path = "/{id}/reject")
    public ResponseEntity<OrderDto> reject(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.reject(id));
    }

}
