package com.ifanmorgan.restaurant.carts;

import com.ifanmorgan.restaurant.carts.dtos.CartDto;
import com.ifanmorgan.restaurant.carts.dtos.CartItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.calculateTotalPrice())")
    CartDto toCartDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotalPrice())")
    CartItemDto toCartItemDto(CartItem cartItem);

}
