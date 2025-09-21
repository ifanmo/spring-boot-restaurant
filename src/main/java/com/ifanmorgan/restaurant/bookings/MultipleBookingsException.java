package com.ifanmorgan.restaurant.bookings;

public class MultipleBookingsException extends RuntimeException {
    public MultipleBookingsException() {
        super("Can't create multiple bookings on the same day");
    }
}
