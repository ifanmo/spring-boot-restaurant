package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    DetailedOrderDto toDetailedOrderDto(Order order);

    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    SimpleOrderDto toSimpleOrderDto(Order order);

    RestaurantCheckoutResponse toRestaurantOrderDto(RestaurantOrder order);

    DeliveryCheckoutResponse toDeliveryOrderDto(DeliveryOrder order);

    TakeawayResponseDto toTakeoutOrderDto(TakeoutOrder order);
}
