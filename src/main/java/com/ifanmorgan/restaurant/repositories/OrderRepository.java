package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}