package com.ifanmorgan.restaurant.menu;

import com.ifanmorgan.restaurant.users.customers.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByCategory(Category category);

    @Query("SELECT mi, SUM(oi.quantity) FROM MenuItem mi JOIN mi.orderItems oi GROUP BY mi ORDER BY SUM(oi.quantity) desc")
    List<MenuItem> findMostPopularMenuItems(Pageable pageable);

  }