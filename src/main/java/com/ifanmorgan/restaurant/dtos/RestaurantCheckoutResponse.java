package com.ifanmorgan.restaurant.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantCheckoutResponse implements OrderDto {
    private Long id;
    private String orderStatus;
}
