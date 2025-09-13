package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
public class CreateTakeoutOrderRequest {
    @NotNull(message = "Customer Id is required")
    private Long customerId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "Pickup time is required")
    private LocalTime pickupTime;
}
