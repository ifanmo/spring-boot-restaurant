package com.ifanmorgan.restaurant.orders;

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
