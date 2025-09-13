package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}