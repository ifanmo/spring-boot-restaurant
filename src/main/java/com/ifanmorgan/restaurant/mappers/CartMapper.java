package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.CartDto;
import com.ifanmorgan.restaurant.dtos.CartItemDto;
import com.ifanmorgan.restaurant.entities.Cart;
import com.ifanmorgan.restaurant.entities.CartItem;
import com.ifanmorgan.restaurant.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.calculateTotalPrice())")
    @Mapping(target = "items", source = "items")
    CartDto toCartDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotalPrice())")
    CartItemDto toCartItemDto(CartItem cartItem);

    @Mapping(target = "unitPrice", source = "item.price")
    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotalPrice())")
    OrderItem toOrderItem(CartItem cartItem);
}
