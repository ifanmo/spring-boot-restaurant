package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.exceptions.CartNotFoundException;
import com.ifanmorgan.restaurant.exceptions.CustomerNotFoundException;
import com.ifanmorgan.restaurant.exceptions.MenuItemNotFoundException;
import com.ifanmorgan.restaurant.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID id) {
        var cartDto = cartService.getCart(id);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart() {
        var cartDto = cartService.createCart();

        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);

    }

    @PostMapping("/{id}/items")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable UUID id,
            @Valid @RequestBody AddItemToCartRequest request
    ) {
        var cartItemDto = cartService.addCartItem(request.getItemId(), id);
        return new ResponseEntity<>(cartItemDto, HttpStatus.CREATED);
    }

    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartItemDto> updateCartItem(
            @Valid @RequestBody updateItemInCartRequest request,
            @PathVariable UUID cartId,
            @PathVariable Long itemId
    ) {
       var cartItemDto = cartService.updateCartItem(request.getQuantity(), cartId, itemId);
       return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Void> deleteCartItem(
            @PathVariable UUID cartId,
            @PathVariable Long itemId) {

        cartService.deleteCartItem(cartId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<Void> clearCart(@PathVariable UUID cartId) {
        cartService.clearCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCartNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Cart not found"));
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMenuItemException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Menu Item not found"));
    }

}
