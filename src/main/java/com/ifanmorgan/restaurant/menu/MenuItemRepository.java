package com.ifanmorgan.restaurant.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByCategory(Category category);
  }