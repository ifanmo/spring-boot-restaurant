package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class TakeoutOrderDto implements OrderDto {
    private Long id;
    private String orderStatus;
    private BigDecimal totalPrice;
    private LocalTime pickupTime;

}
