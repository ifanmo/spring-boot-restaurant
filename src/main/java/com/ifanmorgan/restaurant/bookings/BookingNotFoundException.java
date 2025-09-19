package com.ifanmorgan.restaurant.bookings;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {
        super("Booking not found");
    }
}
