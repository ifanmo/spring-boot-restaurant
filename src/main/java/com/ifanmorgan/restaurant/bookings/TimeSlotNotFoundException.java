package com.ifanmorgan.restaurant.bookings;

public class TimeSlotNotFoundException extends RuntimeException {
    public TimeSlotNotFoundException() {
        super("TimeSlot not found");
    }
}
