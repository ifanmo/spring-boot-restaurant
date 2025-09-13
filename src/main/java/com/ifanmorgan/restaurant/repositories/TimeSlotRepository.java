package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.bookings.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    Optional<TimeSlot> findByStartTime(LocalTime startTime);
}
