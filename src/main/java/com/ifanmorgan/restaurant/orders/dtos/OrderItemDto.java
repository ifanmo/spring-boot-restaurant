package com.ifanmorgan.restaurant.orders.dtos;

import com.ifanmorgan.restaurant.menu.dtos.MenuItemDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private MenuItemDto menuItem;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
