package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.users.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}