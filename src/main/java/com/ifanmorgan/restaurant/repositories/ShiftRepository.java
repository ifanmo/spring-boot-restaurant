package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.users.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}