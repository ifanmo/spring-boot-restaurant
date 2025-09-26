package com.ifanmorgan.restaurant.orders.exceptions;

public class OrderAlreadyPlacedException extends RuntimeException {
    public OrderAlreadyPlacedException() {
        super("Order is already placed");
    }
}
