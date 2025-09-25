package com.ifanmorgan.restaurant.users.customers;

import com.ifanmorgan.restaurant.events.EventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("/customers")
@AllArgsConstructor
@Tag(name = "Customers")
public class CustomerController {
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "A customer can create a profile")
    public ResponseEntity<CustomerDto> createProfile(
            @Valid @RequestBody CreateCustomerProfileRequest request
    ) {
        var customer = customerMapper.toEntity(request);
        var customerDto = customerService.createCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);

    }

    @PostMapping("/events")
    @Operation(summary = "A customer can register from an upcoming event" +
            " providing there are spaces available")
    public ResponseEntity<EventDto> addEvent(
            @Valid @RequestBody AddEventToCustomer request
    ) {
        var eventDto = customerService.addEvent(request.getEventId());
        return ResponseEntity.status(HttpStatus.CREATED).body(eventDto);
    }

    @GetMapping("/me")
    @Operation(summary = "A customer can view their profile")
    public ResponseEntity<CustomerDto> me() {
        var customer = customerService.me();

        var customerDto = customerMapper.toDto(customer);

        return ResponseEntity.ok(customerDto);
    }

}
