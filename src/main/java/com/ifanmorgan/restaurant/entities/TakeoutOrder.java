package com.ifanmorgan.restaurant.entities;

import com.ifanmorgan.restaurant.entities.users.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "TAKEOUT")
public class TakeoutOrder extends Order {
    @Column(name = "pickup_time")
    private LocalTime pickupTime;

    public static TakeoutOrder fromCart(Cart cart, Customer customer, LocalTime pickupTime) {
        var order = new TakeoutOrder();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalPrice(cart.calculateTotalPrice());
        order.setPickupTime(pickupTime);

        cart.getItems().forEach(item -> {
            var orderItem = new OrderItem(order, item.getMenuItem(), item.getQuantity());
            order.getOrderItems().add(orderItem);
        });
        return order;
    }
}