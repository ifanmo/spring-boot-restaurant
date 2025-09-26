package com.ifanmorgan.restaurant.bookings.exceptions;

public class TimeSlotNotFoundException extends RuntimeException {
    public TimeSlotNotFoundException() {
        super("TimeSlot not found");
    }
}
