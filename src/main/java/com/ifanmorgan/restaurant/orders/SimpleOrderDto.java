package com.ifanmorgan.restaurant.orders;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimpleOrderDto implements OrderDto {
    private Long id;
    private BigDecimal totalPrice;
}
