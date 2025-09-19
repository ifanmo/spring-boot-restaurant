package com.ifanmorgan.restaurant.menu;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException() {
        super("MenuItem not found");
    }
}
