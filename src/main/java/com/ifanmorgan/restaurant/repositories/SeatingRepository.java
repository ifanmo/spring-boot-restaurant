package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.Seating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SeatingRepository extends JpaRepository<Seating, Long> {
  @Query("SELECT s FROM Seating s " +
          "WHERE s NOT IN " +
          "(SELECT b.table FROM Booking b" +
          " WHERE b.bookingDate = :bookingDate " +
          "AND b.bookingTimeSlot.startTime = :startTime)")
  Optional<List<Seating>> findAvailableTables(
          @Param("bookingDate") LocalDate bookingDate,
          @Param("startTime") LocalTime startTime);


  @Query("SELECT s FROM Seating s " +
          "WHERE s NOT IN " +
          "(SELECT b.table FROM Booking b" +
          " WHERE b.bookingDate = :bookingDate " +
          "AND b.bookingTimeSlot.startTime = :startTime) " +
          "AND s.capacity >= :guests " +
          "ORDER BY s.capacity" +
          " LIMIT 1")
  Optional<Seating> findAvailableTableForBooking(
          @Param("bookingDate") LocalDate bookingDate,
          @Param("startTime") LocalTime startTime,
          @Param("guests") Integer guests);


  }