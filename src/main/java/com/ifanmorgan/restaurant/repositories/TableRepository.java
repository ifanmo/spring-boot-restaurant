package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {

  }