package com.ifanmorgan.restaurant.events;

public class SameDayEventException extends RuntimeException {
    public SameDayEventException() {
        super("Cannot book two events on the same day");
    }
}
