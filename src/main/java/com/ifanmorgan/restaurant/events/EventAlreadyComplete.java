package com.ifanmorgan.restaurant.events;

public class EventAlreadyComplete extends RuntimeException {
    public EventAlreadyComplete() {
        super("Event already complete");
    }
}
