package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.CartDto;
import com.ifanmorgan.restaurant.dtos.CartItemDto;
import com.ifanmorgan.restaurant.entities.Cart;
import com.ifanmorgan.restaurant.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.calculateTotalPrice())")
    @Mapping(target = "items", source = "cartItems")
    CartDto toCartDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotalPrice())")
    CartItemDto toCartItemDto(CartItem cartItem);
}
