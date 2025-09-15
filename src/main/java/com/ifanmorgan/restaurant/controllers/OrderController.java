package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.exceptions.CustomerNotFoundException;
import com.ifanmorgan.restaurant.exceptions.MenuItemNotFoundException;
import com.ifanmorgan.restaurant.exceptions.OrderAlreadyPlacedException;
import com.ifanmorgan.restaurant.exceptions.OrderNotFoundException;
import com.ifanmorgan.restaurant.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
class OrderController {

    private final OrderService orderService;
    @PostMapping("/restaurant")
    public ResponseEntity<OrderDto> createRestaurantOrder(
            @Valid @RequestBody CreateRestaurantOrderRequest request) {
        var orderDto = orderService.createRestaurantOrder(request);

        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);

    }
    @PostMapping("/takeout")
    public ResponseEntity<OrderDto> createTakeoutOrder(
            @Valid @RequestBody CreateTakeoutOrderRequest request) {
        var orderDto = orderService.createTakeoutOrder(request);

        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);

    }

    @PostMapping("/delivery")
    public ResponseEntity<OrderDto> createDeliveryOrder(
            @Valid @RequestBody CreateDeliveryOrderRequest request) {
        var orderDto = orderService.createDeliveryOrder(request);

        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable UUID id) {
        var orderDto = orderService.getOrderById(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping("/{id}/place-order")
    public ResponseEntity<Void> placeOrder(@PathVariable UUID id) {
        orderService.placeOrder(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/complete-order")
    public ResponseEntity<Void> completeOrder(@PathVariable UUID id) {
        orderService.completeOrder(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/items")
    public ResponseEntity<OrderItemDto> addItemToOrder(
            @PathVariable UUID id,
            @Valid @RequestBody AddItemToOrderRequest request
            ) {
        var orderItemDto = orderService.addOrderItem(request.getItemId(), id);
        return new ResponseEntity<>(orderItemDto, HttpStatus.CREATED);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Customer not found"));
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
