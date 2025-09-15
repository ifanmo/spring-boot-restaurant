package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
  }