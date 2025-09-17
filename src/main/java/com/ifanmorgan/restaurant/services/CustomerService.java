package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.CreateCustomerProfileRequest;
import com.ifanmorgan.restaurant.dtos.CustomerDto;
import com.ifanmorgan.restaurant.entities.users.Customer;
import com.ifanmorgan.restaurant.entities.users.Role;
import com.ifanmorgan.restaurant.mappers.CustomerMapper;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CustomerMapper customerMapper;


    public CustomerDto createCustomer(Customer customer) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long)authentication.getPrincipal();
        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!user.getRole().equals(Role.CUSTOMER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only customers can create customer profiles");
        }

        customer.setUser(user);
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }
}
