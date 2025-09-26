package com.ifanmorgan.restaurant.orders.dtos;

import com.ifanmorgan.restaurant.orders.OrderType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class CheckoutRequest {
    @NotNull(message = "cart id is required")
    private UUID cartId;
    @NotNull(message = "order type is required")
    private OrderType orderType;
    private LocalTime deliveryTime;
    private LocalTime pickupTime;
}
