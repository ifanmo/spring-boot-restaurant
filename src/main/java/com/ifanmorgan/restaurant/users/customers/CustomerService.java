package com.ifanmorgan.restaurant.users.customers;

import com.ifanmorgan.restaurant.auth.AuthService;
import com.ifanmorgan.restaurant.events.*;
import com.ifanmorgan.restaurant.events.exceptions.EventFullyBookedException;
import com.ifanmorgan.restaurant.events.exceptions.EventNotFoundException;
import com.ifanmorgan.restaurant.users.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
