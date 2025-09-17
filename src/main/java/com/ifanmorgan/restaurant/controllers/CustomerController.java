package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.CreateCustomerProfileRequest;
import com.ifanmorgan.restaurant.dtos.CustomerDto;
import com.ifanmorgan.restaurant.entities.users.Role;
import com.ifanmorgan.restaurant.mappers.CustomerMapper;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.UserRepository;
import com.ifanmorgan.restaurant.services.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createProfile(
            @Valid @RequestBody CreateCustomerProfileRequest request
    ) {
        var customer = customerMapper.toEntity(request);
        var customerDto = customerService.createCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);

    }
}
