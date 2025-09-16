package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.CartDto;
import com.ifanmorgan.restaurant.entities.Cart;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
