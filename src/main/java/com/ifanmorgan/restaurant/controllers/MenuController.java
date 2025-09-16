package com.ifanmorgan.restaurant.controllers;


import com.ifanmorgan.restaurant.dtos.MenuItemDto;
import com.ifanmorgan.restaurant.dtos.updateSpecialRequest;
import com.ifanmorgan.restaurant.exceptions.MenuItemNotFoundException;
import com.ifanmorgan.restaurant.services.MenuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuItemDto>> getAllItems() {
        return ResponseEntity.ok(menuService.getAllItems());
    }

    @PostMapping("/special")
    public ResponseEntity<MenuItemDto> createOrUpdateSpecial(
            @Valid @RequestBody updateSpecialRequest request
    ) {
        var dto = menuService.createOrUpdateSpecial(request.getName(), request.getPrice(), request.getDescription());
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMenuItemNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Menu Item not found"));
    }
}
