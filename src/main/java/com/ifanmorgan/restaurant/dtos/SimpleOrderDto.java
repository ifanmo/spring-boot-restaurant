package com.ifanmorgan.restaurant.dtos;

import com.ifanmorgan.restaurant.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SimpleOrderDto {
    private Long id;
    private String orderStatus;
    private BigDecimal totalPrice;
}
