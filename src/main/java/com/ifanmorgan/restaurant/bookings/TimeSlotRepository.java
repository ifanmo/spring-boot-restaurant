package com.ifanmorgan.restaurant.bookings;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    Optional<TimeSlot> findByStartTime(LocalTime startTime);

    @Query("SELECT t, COUNT(b) FROM TimeSlot t JOIN t.bookings b GROUP BY t ORDER BY COUNT(b) desc")
    List<TimeSlot> getBusiestPeriods(Pageable pageable);


}
