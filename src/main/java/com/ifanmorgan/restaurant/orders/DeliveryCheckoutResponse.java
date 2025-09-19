package com.ifanmorgan.restaurant.orders;

import lombok.Data;

import java.time.LocalTime;

@Data
public class DeliveryCheckoutResponse implements OrderDto {
    private Long id;
    private String orderStatus;
    private LocalTime deliveryTime;
}
