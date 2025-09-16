package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.CartDto;
import com.ifanmorgan.restaurant.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart() {
        var cartDto = cartService.createCart();

        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);

    }

}
