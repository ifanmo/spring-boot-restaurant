package com.ifanmorgan.restaurant.users.staff;

public class StaffNotFoundException extends RuntimeException {
    public StaffNotFoundException() {
        super("Staff not found");
    }
}
