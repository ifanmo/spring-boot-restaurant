package com.ifanmorgan.restaurant.events;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Event not found");
    }
}
