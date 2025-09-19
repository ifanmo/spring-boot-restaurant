package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.exceptions.*;
import com.ifanmorgan.restaurant.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
class OrderController {
    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(
            @Valid @RequestBody CheckoutRequest request) {

            var orderDto = orderService.checkout(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
        }


    @GetMapping
    public ResponseEntity<List<SimpleOrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedOrderDto> getOrder(@PathVariable Long id) {
        var orderDto = orderService.getOrder(id);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/outstanding")
    public ResponseEntity<List<SimpleOrderDto>> getOutstandingOrders() {
        return ResponseEntity.ok(orderService.getOutstandingOrders());
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        orderService.approve(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        orderService.complete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Customer not found"));
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFoundException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Cart not found"));
    }

    @ExceptionHandler(CartIsEmptyException.class)
    public ResponseEntity<Map<String, String>> handleCartIsEmptyException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Cart is empty"));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOrderNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Order not found"));
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMenuItemNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Menu Item not found"));
    }

    @ExceptionHandler(OrderAlreadyPlacedException.class)
    public ResponseEntity<Map<String, String>> handleOrderAlreadyPlaced() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Order already placed."));
    }

}
