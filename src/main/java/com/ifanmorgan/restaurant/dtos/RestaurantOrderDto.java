package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantOrderDto {
    private Long id;
    private Long customerId;
    private List<OrderItemDto> items = new ArrayList<>();

}
