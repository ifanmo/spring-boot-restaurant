package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}