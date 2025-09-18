package com.ifanmorgan.restaurant.dtos;

import com.ifanmorgan.restaurant.entities.OrderType;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class CheckoutRequest {
    private UUID cartId;
    private OrderType orderType;
    private LocalTime deliveryTime;
    private LocalTime pickupTime;
}
