package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.Order;
import com.ifanmorgan.restaurant.entities.OrderStatus;
import com.ifanmorgan.restaurant.entities.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderStatus(OrderStatus orderStatus);

    Optional<Order> findByIdAndOrderStatus(Long orderId, OrderStatus orderStatus);

}