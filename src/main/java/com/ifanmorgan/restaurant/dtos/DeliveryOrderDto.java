package com.ifanmorgan.restaurant.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class DeliveryOrderDto {
    private UUID id;
    private Long customerId;
    private String streetNumber;
    private String street;
    private String postcode;
    private List<OrderItemDto> items = new ArrayList<>();
}
