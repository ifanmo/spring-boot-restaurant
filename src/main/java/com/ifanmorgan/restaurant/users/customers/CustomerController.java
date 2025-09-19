package com.ifanmorgan.restaurant.users.customers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
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

    @GetMapping("/me")
    public ResponseEntity<CustomerDto> me() {
        var customer = customerService.me();

        var customerDto = customerMapper.toDto(customer);

        return ResponseEntity.ok(customerDto);
    }
}
