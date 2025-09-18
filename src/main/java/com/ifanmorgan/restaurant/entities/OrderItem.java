package com.ifanmorgan.restaurant.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private MenuItem item;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_Price")
    private BigDecimal totalPrice;

    public OrderItem(MenuItem item, Integer quantity, BigDecimal price, BigDecimal totalPrice, Order order) {
        this.item = item;
        this.quantity = quantity;
        this.unitPrice = price;
        this.totalPrice = totalPrice;
        this.order = order;
    }

    public BigDecimal calculateTotalPrice() {
        return item.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

}