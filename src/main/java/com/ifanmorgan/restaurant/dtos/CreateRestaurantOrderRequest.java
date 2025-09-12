package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRestaurantOrderRequest {
    @NotNull(message = "Customer Id is required")
    private Long customerId;
}
