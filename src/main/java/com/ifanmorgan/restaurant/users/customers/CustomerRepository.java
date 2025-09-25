package com.ifanmorgan.restaurant.users.customers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c, COUNT(o) FROM Customer c JOIN c.orders o GROUP BY c ORDER BY COUNT(o) desc")
    List<Customer> findMostActiveCustomer(Pageable pageable);
}