package com.ifanmorgan.restaurant.orders;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TakeawayResponseDto implements OrderDto {
    private Long id;
    private String orderStatus;
    private LocalTime pickupTime;

}
