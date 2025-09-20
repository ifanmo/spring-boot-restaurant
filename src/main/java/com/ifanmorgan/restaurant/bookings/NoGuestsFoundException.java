package com.ifanmorgan.restaurant.bookings;

public class NoGuestsFoundException extends RuntimeException {
    public NoGuestsFoundException() {
        super("No guests found");
    }
}
