package com.ifanmorgan.restaurant.carts.dtos;

import com.ifanmorgan.restaurant.menu.dtos.MenuItemDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private MenuItemDto menuItem;
    private Integer quantity;
    private BigDecimal totalPrice;
}
