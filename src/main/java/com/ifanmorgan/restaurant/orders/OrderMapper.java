package com.ifanmorgan.restaurant.orders;

import com.ifanmorgan.restaurant.orders.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    DetailedOrderDto toDetailedOrderDto(Order order);

    @Mapping(target = "totalPrice", expression = "java(order.calculateTotalPrice())")
    @Mapping(target = "orderStatus", source = "orderStatus")
    SimpleOrderDto toSimpleOrderDto(Order order);

    RestaurantCheckoutResponse toRestaurantOrderDto(RestaurantOrder order);

    DeliveryCheckoutResponse toDeliveryOrderDto(DeliveryOrder order);

    TakeawayResponseDto toTakeoutOrderDto(TakeoutOrder order);
}
