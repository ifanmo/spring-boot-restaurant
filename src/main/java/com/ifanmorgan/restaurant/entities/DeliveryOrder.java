package com.ifanmorgan.restaurant.entities;

import com.ifanmorgan.restaurant.entities.users.Customer;
import com.ifanmorgan.restaurant.entities.users.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "DELIVERY")
public class DeliveryOrder extends Order {
    @Column(name = "delivery_status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column(name = "delivery_time")
    private LocalTime deliveryTime;

    @ManyToOne
    @JoinColumn(name = "driver")
    private Staff driver;

    public static DeliveryOrder fromCart(Cart cart, Customer customer) {
        var order = new DeliveryOrder();
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