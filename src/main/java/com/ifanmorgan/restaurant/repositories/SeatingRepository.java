package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.Seating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatingRepository extends JpaRepository<Seating, Long> {
  }