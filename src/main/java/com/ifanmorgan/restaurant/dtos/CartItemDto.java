package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private MenuItemDto item;
    private Integer quantity;
    private BigDecimal totalPrice;
}
