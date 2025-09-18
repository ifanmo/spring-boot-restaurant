package com.ifanmorgan.restaurant.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RestaurantOrderDto implements OrderDto {
    private Long id;
    private String orderStatus;
    private BigDecimal totalPrice;
}
