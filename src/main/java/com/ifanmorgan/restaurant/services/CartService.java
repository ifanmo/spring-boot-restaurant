package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.CartDto;
import com.ifanmorgan.restaurant.entities.Cart;
import com.ifanmorgan.restaurant.mappers.CartMapper;
import com.ifanmorgan.restaurant.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private CartMapper mapper;

    public CartDto createCart() {
        var cart = new Cart();
        cart = cartRepository.save(cart);
        return mapper.toDto(cart);
    }
}
