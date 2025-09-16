package com.ifanmorgan.restaurant.dtos;

import java.math.BigDecimal;

public class CartItemDto {
    private MenuItemDto item;
    private Integer quantity;
    private BigDecimal totalPrice;
}
