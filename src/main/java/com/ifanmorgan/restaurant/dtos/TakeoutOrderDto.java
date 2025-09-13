package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class TakeoutOrderDto {
    private UUID id;
    private Long customerId;
    private LocalTime pickupTime;
    private List<OrderItemDto> items = new ArrayList<>();
}
