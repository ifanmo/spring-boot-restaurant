package com.ifanmorgan.restaurant.orders;

public class OrderAlreadyPlacedException extends RuntimeException {
    public OrderAlreadyPlacedException() {
        super("Order is already placed");
    }
}
