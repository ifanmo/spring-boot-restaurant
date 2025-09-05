package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Data
public class CreateBookingRequest {
    @Min(value = 2, message = "Minimum number of guests is 2")
    @Max(value = 10, message = "Maximum number of guests is 10")
    @NotNull(message = "Number of guests is required")
    private Integer guests;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "Date cannot be in the past")
    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "Booking time is required")
    private LocalTime bookingTime;
    @NotNull(message = "Customer Id is required")
    private Long customerId;

    private Boolean extendBooking;
}
