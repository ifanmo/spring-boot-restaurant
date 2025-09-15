package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    @Mapping(target = "items", source = "orderItems")
    OrderDto toOrderDto(Order order);

    @Mapping(target = "totalPrice", expression = "java(orderItem.calculateTotalPrice())")

    OrderItemDto toOrderItemDto(OrderItem orderItem);
}
