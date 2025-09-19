package com.ifanmorgan.restaurant.carts;

public class CartIsEmptyException extends RuntimeException {
    public CartIsEmptyException() {
        super("Cart is empty");
    }
}
