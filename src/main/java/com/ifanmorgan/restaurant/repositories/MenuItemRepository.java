package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.Category;
import com.ifanmorgan.restaurant.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByCategory(Category category);
  }