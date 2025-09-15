package com.ifanmorgan.restaurant.dtos;

import com.ifanmorgan.restaurant.entities.MenuItem;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private MenuItemDto item;
    private Integer quantity;
    private BigDecimal totalPrice;
}
