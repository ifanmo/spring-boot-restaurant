package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemToOrderRequest {
    @NotNull(message = "Menu Item Cannot Be Null")
    private Long itemId;
}
