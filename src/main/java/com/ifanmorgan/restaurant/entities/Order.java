package com.ifanmorgan.restaurant.entities;

import com.ifanmorgan.restaurant.entities.users.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "orders")
public abstract class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "status", insertable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    public OrderItem getItem(Long itemId) {
        return orderItems.stream().filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
    }

    public OrderItem addItem(MenuItem item) {
        var orderItem = this.getItem(item.getId());
        if (orderItem != null) {
            orderItem.setQuantity(orderItem.getQuantity() + 1);
        } else {
            orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setQuantity(1);
            orderItem.setOrder(this);
            orderItems.add(orderItem);
        }
        return orderItem;
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public BigDecimal calculateTotalPrice() {
        return orderItems.stream()
                .map(OrderItem::calculateTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}