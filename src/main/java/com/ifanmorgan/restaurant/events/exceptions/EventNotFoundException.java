package com.ifanmorgan.restaurant.events.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException() {
        super("Event not found");
    }
}
