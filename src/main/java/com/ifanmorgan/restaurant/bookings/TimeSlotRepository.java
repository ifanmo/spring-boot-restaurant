package com.ifanmorgan.restaurant.bookings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    Optional<TimeSlot> findByStartTime(LocalTime startTime);
}
