package com.ifanmorgan.restaurant.entities;

import com.ifanmorgan.restaurant.entities.users.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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

    public static TakeoutOrder fromCart(Cart cart, Customer customer) {
        var order = new TakeoutOrder();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setTotalPrice(cart.calculateTotalPrice());

        cart.getItems().forEach(item -> {
            var orderItem = new OrderItem(order, item.getMenuItem(), item.getQuantity());
            order.getOrderItems().add(orderItem);
        });
        return order;
    }
}