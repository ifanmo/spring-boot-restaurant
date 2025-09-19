package com.ifanmorgan.restaurant.events;

public class EventAlreadyApprovedException extends RuntimeException {
    public EventAlreadyApprovedException() {
        super("Event already approved");
    }
}
