package com.ifanmorgan.restaurant.dtos;

import com.ifanmorgan.restaurant.entities.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateCartResponse {
    private UUID id;
    private MenuItem item;
}
