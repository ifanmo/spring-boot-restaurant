package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    SimpleOrderDto toOrderDto(Order order);

    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    RestaurantOrderDto toRestaurantOrderDto(RestaurantOrder order);

    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    DeliveryOrderDto toDeliveryOrderDto(DeliveryOrder order);

    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    TakeoutOrderDto toTakeoutOrderDto(TakeoutOrder order);
}
