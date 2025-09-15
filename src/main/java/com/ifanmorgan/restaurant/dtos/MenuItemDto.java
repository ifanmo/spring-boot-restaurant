package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
}
