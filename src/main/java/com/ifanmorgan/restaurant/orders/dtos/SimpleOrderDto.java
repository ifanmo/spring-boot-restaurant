package com.ifanmorgan.restaurant.orders.dtos;


import com.ifanmorgan.restaurant.orders.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimpleOrderDto implements OrderDto {
    private Long id;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
}
