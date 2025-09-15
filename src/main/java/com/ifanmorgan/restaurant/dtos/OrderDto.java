package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
}
