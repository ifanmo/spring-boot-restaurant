package com.ifanmorgan.restaurant.bookings.exceptions;

public class TablesNotFoundException extends RuntimeException {
    public TablesNotFoundException() {
        super("Tables not found");
    }
}
