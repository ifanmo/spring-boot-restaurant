package com.ifanmorgan.restaurant.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String role;
}
