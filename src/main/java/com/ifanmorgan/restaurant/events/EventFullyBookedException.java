package com.ifanmorgan.restaurant.events;

public class EventFullyBookedException extends RuntimeException {
    public EventFullyBookedException() {
        super("Event fully booked!");
    }
}
