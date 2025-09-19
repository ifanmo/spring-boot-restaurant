package com.ifanmorgan.restaurant.orders;

import com.ifanmorgan.restaurant.menu.MenuItemDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private MenuItemDto menuItem;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
