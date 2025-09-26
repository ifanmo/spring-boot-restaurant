package com.ifanmorgan.restaurant.bookings.exceptions;

public class NoGuestsFoundException extends RuntimeException {
    public NoGuestsFoundException() {
        super("No guests found");
    }
}
