package com.ifanmorgan.restaurant.events.exceptions;

public class EventFullyBookedException extends RuntimeException {
    public EventFullyBookedException() {
        super("Event fully booked!");
    }
}
