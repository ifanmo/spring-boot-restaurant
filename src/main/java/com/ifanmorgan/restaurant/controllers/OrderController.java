package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.CreateRestaurantOrderRequest;
import com.ifanmorgan.restaurant.dtos.RestaurantOrderDto;
import com.ifanmorgan.restaurant.exceptions.CustomerNotFoundException;
import com.ifanmorgan.restaurant.services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
class OrderController {

    private final OrderService orderService;
    @PostMapping("/restaurant")
    public ResponseEntity<?> createRestaurantOrder(
            @Valid @RequestBody CreateRestaurantOrderRequest request) {
        var orderDto = orderService.createRestaurantOrder(request);

        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);

    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Customer not found"));
    }

}
