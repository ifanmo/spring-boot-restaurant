package com.ifanmorgan.restaurant.services;

import org.springframework.stereotype.Service;

@Service
public class BookingNotificationHandler implements NotificationHandler {
    @Override
    public void handle(String notification) {
        System.out.println(notification);
    }
}
