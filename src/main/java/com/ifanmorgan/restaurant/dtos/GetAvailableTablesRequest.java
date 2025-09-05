package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GetAvailableTablesRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "Date cannot be in the past")
    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "Booking time is required")
    private LocalTime startTime;
}
