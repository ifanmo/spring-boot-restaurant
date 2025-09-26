package com.ifanmorgan.restaurant.bookings.repositories;

import com.ifanmorgan.restaurant.bookings.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT SUM(b.guests) FROM Booking b WHERE b.bookingDate = CURRENT_DATE()")
    Integer getNumberOfGuests();

    @Query("SELECT COUNT(*) FROM Booking b WHERE b.bookingDate = CURRENT_DATE()")
    Integer getNumberOfTables();

    Boolean existsByCustomerIdAndBookingDate(Long customerId, LocalDate bookingDate);
  }