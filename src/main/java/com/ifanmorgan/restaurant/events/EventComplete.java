package com.ifanmorgan.restaurant.events;

public class EventComplete extends RuntimeException {
    public EventComplete() {
        super("Event complete");
    }
}
