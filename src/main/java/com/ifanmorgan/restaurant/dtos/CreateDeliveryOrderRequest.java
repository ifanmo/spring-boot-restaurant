package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDeliveryOrderRequest {
    @NotNull(message = "Customer Id is required")
    private Long customerId;
}
