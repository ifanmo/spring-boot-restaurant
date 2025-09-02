package com.ifanmorgan.restaurant.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class CreateBookingRequest {
    private Integer numberOfGuests;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private Long bookingTimeSlotId;
    private Long customerId;
}
