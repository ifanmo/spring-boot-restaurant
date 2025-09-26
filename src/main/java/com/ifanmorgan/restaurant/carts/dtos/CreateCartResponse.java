package com.ifanmorgan.restaurant.carts.dtos;

import com.ifanmorgan.restaurant.menu.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateCartResponse {
    private UUID id;
    private MenuItem item;
}
