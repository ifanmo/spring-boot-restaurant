package com.ifanmorgan.restaurant.carts;

import com.ifanmorgan.restaurant.orders.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.calculateTotalPrice())")
    CartDto toCartDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotalPrice())")
    CartItemDto toCartItemDto(CartItem cartItem);

    @Mapping(target = "unitPrice", source = "menuItem.price")
    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotalPrice())")
    OrderItem toOrderItem(CartItem cartItem);
}
