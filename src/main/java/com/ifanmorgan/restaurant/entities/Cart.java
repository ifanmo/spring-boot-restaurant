package com.ifanmorgan.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CartItem> cartItems = new LinkedHashSet<>();

    public CartItem getItem(Long itemId) {
        for (var item : cartItems) {
            if (item.getItem().getId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    public CartItem addItem(MenuItem item) {
        var cartItem = this.getItem(item.getId());
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(1);
            cartItem.setCart(this);
            this.cartItems.add(cartItem);
        }
        return cartItem;
    }

    public void removeItem(Long itemId) {
        var cartItem = this.getItem(itemId);
        if (cartItem != null) {
            cartItems.remove(cartItem);
        }
    }

    public void clearCart() {
        this.getCartItems().clear();
    }


    public BigDecimal calculateTotalPrice() {
        return cartItems.stream()
                .map(CartItem::calculateTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}