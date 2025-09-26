package com.ifanmorgan.restaurant.orders;

import com.ifanmorgan.restaurant.orders.dtos.CheckoutRequest;
import com.ifanmorgan.restaurant.orders.dtos.DetailedOrderDto;
import com.ifanmorgan.restaurant.orders.dtos.SimpleOrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Orders")
class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/checkout")
    @Operation(summary = "A customer can create an order from a cart")
    public ResponseEntity<?> checkout(
            @Valid @RequestBody CheckoutRequest request) {

            var orderDto = orderService.checkout(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
        }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER')")
    @GetMapping
    public ResponseEntity<List<SimpleOrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER', 'DELIVERY_DRIVER')")
    @GetMapping("/{id}")
    @Operation(summary = "A staff member can get a single order")
    public ResponseEntity<DetailedOrderDto> getOrder(@PathVariable Long id) {
        var orderDto = orderService.getOrder(id);
        return ResponseEntity.ok(orderDto);
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER', 'DELIVERY_DRIVER')")
    @GetMapping("/outstanding")
    @Operation(summary = "A staff member can get all orders that have been approved but are not complete")
    public ResponseEntity<List<SimpleOrderDto>> getOutstandingOrders() {
        return ResponseEntity.ok(orderService.getOutstandingOrders());
    }

    @PreAuthorize("hasRole('WAITER')")
    @PostMapping("/{id}/approve")
    @Operation(summary = "A waiter can mark an order as approved")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        orderService.approve(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('CHEF')")
    @PostMapping("/{id}/complete")
    @Operation(summary = "A chef can mark an order as complete")
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        orderService.complete(id);
        return ResponseEntity.ok().build();
    }

}
