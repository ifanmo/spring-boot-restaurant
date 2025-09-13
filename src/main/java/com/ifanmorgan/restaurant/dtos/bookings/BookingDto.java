package com.ifanmorgan.restaurant.dtos.bookings;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingDto {
    Integer guests;
    LocalTime bookingTime;
    LocalDate bookingDate;
    String status;
    Long customerId;
    Long tableId;
}