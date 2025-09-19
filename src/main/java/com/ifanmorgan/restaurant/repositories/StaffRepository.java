package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.users.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}