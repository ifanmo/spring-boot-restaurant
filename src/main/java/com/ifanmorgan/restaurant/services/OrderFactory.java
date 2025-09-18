package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.entities.*;
import com.ifanmorgan.restaurant.entities.users.Customer;
import org.springframework.stereotype.Component;

import static com.ifanmorgan.restaurant.entities.OrderType.*;

@Component
public class OrderFactory {
    public Order create(OrderType orderType, Cart cart, Customer customer) {
        return switch (orderType) {
            case RESTAURANT -> RestaurantOrder.fromCart(cart, customer);
            case TAKEAWAY -> TakeoutOrder.fromCart(cart, customer);
            case DELIVERY -> DeliveryOrder.fromCart(cart, customer);
        };
    }
}
