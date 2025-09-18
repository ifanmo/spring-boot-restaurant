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
    public ResponseEntity<SimpleOrderDto> checkout(
            @Valid @RequestBody CheckoutRequest request) {
        var simpleOrderDto = orderService.checkout(request.getOrderType(), request.getCartId());
        return new ResponseEntity<>(simpleOrderDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        var orderDto = orderService.getOrder(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping("/outstanding")
    public ResponseEntity<List<OrderDto>> getOutstandingOrders() {
        return ResponseEntity.ok(orderService.getOutstandingOrders());
    }

    @PostMapping("/{id}/complete-order")
    public ResponseEntity<Void> completeOrder(@PathVariable Long id) {
        orderService.completeOrder(id);
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
