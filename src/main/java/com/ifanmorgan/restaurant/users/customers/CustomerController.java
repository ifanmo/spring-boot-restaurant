package com.ifanmorgan.restaurant.users.customers;

import com.ifanmorgan.restaurant.events.EventDto;
import com.ifanmorgan.restaurant.events.EventFullyBookedException;
import com.ifanmorgan.restaurant.events.EventNotFoundException;
import com.ifanmorgan.restaurant.misc.ErrorDto;
import com.ifanmorgan.restaurant.users.UserNotFoundException;
import com.ifanmorgan.restaurant.users.staff.StaffDto;
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

    @PostMapping("/events")
    public ResponseEntity<EventDto> addEvent(
            @Valid @RequestBody AddEventToCustomer request
    ) {
        var eventDto = customerService.addEvent(request.getEventId());
        return ResponseEntity.status(HttpStatus.CREATED).body(eventDto);
    }

    @GetMapping("/me")
    public ResponseEntity<CustomerDto> me() {
        var customer = customerService.me();

        var customerDto = customerMapper.toDto(customer);

        return ResponseEntity.ok(customerDto);
    }

    @ExceptionHandler({UserNotFoundException.class, CustomerNotFoundException.class, EventNotFoundException.class})
    public ResponseEntity<ErrorDto> handleNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler({EventFullyBookedException.class})
    public ResponseEntity<ErrorDto> handleBadRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }
}
