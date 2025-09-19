package com.ifanmorgan.restaurant.users.customers;

import com.ifanmorgan.restaurant.auth.AuthService;
import com.ifanmorgan.restaurant.users.Role;
import com.ifanmorgan.restaurant.users.UserNotFoundException;
import com.ifanmorgan.restaurant.users.UserRepository;
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
}
