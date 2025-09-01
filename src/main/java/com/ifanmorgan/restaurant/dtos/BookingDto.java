package com.ifanmorgan.restaurant.dtos;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingDto {
    Integer numberOfGuests;
    LocalTime startTime;
    Integer duration;
    LocalDate date;
    String status;
    Long customerId;
    Long tableId;
}