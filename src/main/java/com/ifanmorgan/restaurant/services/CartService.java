package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.CartDto;
import com.ifanmorgan.restaurant.dtos.CartItemDto;
import com.ifanmorgan.restaurant.entities.Cart;
import com.ifanmorgan.restaurant.exceptions.CartNotFoundException;
import com.ifanmorgan.restaurant.exceptions.MenuItemNotFoundException;
import com.ifanmorgan.restaurant.exceptions.OrderNotFoundException;
import com.ifanmorgan.restaurant.mappers.CartMapper;
import com.ifanmorgan.restaurant.repositories.CartRepository;
import com.ifanmorgan.restaurant.repositories.MenuItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private MenuItemRepository itemRepository;
    private CartMapper mapper;

    public CartDto createCart() {
        var cart = new Cart();
        cart = cartRepository.save(cart);
        return mapper.toCartDto(cart);
    }

    public CartItemDto addCartItem(Long itemId, UUID id) {
        var cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        var item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            throw new MenuItemNotFoundException();
        }

        var cartItem = cart.addItem(item);

        cartRepository.save(cart);

        return mapper.toCartItemDto(cartItem);
    }

    public CartDto getCart(UUID id) {
        var cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            throw new OrderNotFoundException();
        }

        return mapper.toCartDto(cart);
    }

    public CartItemDto updateCartItem(Integer quantity, UUID cartId, Long itemId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        var item = cart.getItem(itemId);

        if (cart.getItem(itemId) == null) {
            throw new MenuItemNotFoundException();
        }

        item.setQuantity(quantity);
        cartRepository.save(cart);
        return mapper.toCartItemDto(item);
    }

    public void deleteCartItem(UUID cartId, Long itemId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        cart.removeItem(itemId);
        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        cart.clearCart();
        cartRepository.save(cart);
    }
}
