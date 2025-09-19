package com.ifanmorgan.restaurant.bookings;

public class TablesNotFoundException extends RuntimeException {
    public TablesNotFoundException() {
        super("Tables not found");
    }
}
