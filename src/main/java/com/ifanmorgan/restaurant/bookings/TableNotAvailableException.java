package com.ifanmorgan.restaurant.bookings;

public class TableNotAvailableException extends RuntimeException {
    public TableNotAvailableException() {
        super("Table not available");
    }
}
