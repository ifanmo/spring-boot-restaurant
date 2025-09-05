package com.ifanmorgan.restaurant.repositories;

import com.ifanmorgan.restaurant.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
    @Procedure("findAvailableTables")
    Optional<List<RestaurantTable>> findAvailableTables(LocalTime startTime, LocalTime endTime, LocalDate bookingDate);


    @Procedure("findTableForBooking")
    Optional<RestaurantTable> findTableForBooking(LocalDate bookingDate, LocalTime startTime, LocalTime endTime, Integer guests);
  }