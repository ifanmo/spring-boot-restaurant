package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class RestaurantOrderDto {
    private UUID id;
    private Long customerId;
    private List<OrderItemDto> items = new ArrayList<>();

}
