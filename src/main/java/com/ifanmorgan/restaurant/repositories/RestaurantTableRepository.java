package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}