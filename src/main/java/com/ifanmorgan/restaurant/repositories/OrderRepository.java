package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrderRepository extends JpaRepository<Order, Long> {
}