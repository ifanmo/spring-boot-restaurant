package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GetAvailableTablesRequest {
    private LocalDate bookingDate;
    private LocalTime startTime;
}
