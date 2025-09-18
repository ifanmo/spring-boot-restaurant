package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ShiftDto {
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
}
