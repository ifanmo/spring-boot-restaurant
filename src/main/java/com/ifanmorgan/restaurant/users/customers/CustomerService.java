package com.ifanmorgan.restaurant.users.customers;

import com.ifanmorgan.restaurant.auth.AuthService;
import com.ifanmorgan.restaurant.events.*;
import com.ifanmorgan.restaurant.users.Role;
import com.ifanmorgan.restaurant.users.UserNotFoundException;
import com.ifanmorgan.restaurant.users.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AuthService authService;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;


    public CustomerDto createCustomer(Customer customer) {
        var user = authService.getCurrentUser();
        if (user == null) {
            throw new UserNotFoundException();
        }

        customer.setUser(user);
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    public Customer me() {
        var user = authService.getCurrentUser();
        if (user == null) {
            throw new UserNotFoundException();
        }
        var customer = customerRepository.findById(user.getId()).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    public EventDto addEvent(Long eventId)  {
        var user = authService.getCurrentUser();
        var customer = customerRepository.findById(user.getId()).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        var event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            throw new EventNotFoundException();
        }

        if (event.isFullyBooked()) {
            throw new EventFullyBookedException();
        }

        customer.addEvent(event);
        customerRepository.save(customer);
        return eventMapper.toDto(event);

    }
}
