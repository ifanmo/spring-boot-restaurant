package com.ifanmorgan.restaurant.events.exceptions;

public class EventAlreadyApprovedException extends RuntimeException {
    public EventAlreadyApprovedException() {
        super("Event already approved");
    }
}
