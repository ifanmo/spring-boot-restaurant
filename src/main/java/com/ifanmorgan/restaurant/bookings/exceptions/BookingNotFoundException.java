package com.ifanmorgan.restaurant.bookings.exceptions;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {
        super("Booking not found");
    }
}
