package com.ifanmorgan.restaurant.carts;

import com.ifanmorgan.restaurant.menu.MenuItemNotFoundException;
import com.ifanmorgan.restaurant.misc.ErrorDto;
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
    public ResponseEntity<CreateCartResponse> createCart() {
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
            @Valid @RequestBody UpdateItemInCartRequest request,
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

    @ExceptionHandler({CartNotFoundException.class, MenuItemNotFoundException.class})
    public ResponseEntity<ErrorDto> handleCartNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }

}
