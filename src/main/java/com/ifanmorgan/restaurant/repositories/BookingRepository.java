package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}