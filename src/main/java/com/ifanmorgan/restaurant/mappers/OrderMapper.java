package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.DeliveryOrderDto;
import com.ifanmorgan.restaurant.dtos.RestaurantOrderDto;
import com.ifanmorgan.restaurant.dtos.TakeoutOrderDto;
import com.ifanmorgan.restaurant.entities.DeliveryOrder;
import com.ifanmorgan.restaurant.entities.RestaurantOrder;
import com.ifanmorgan.restaurant.entities.TakeoutOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    RestaurantOrderDto toRestaurantDto(RestaurantOrder order);

    @Mapping(target = "customerId", source = "customer.id")
    TakeoutOrderDto toTakeoutDto (TakeoutOrder order);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "streetNumber", source = "customer.streetNumber")
    @Mapping(target = "street", source = "customer.street")
    @Mapping(target = "postcode", source = "customer.postcode")
    DeliveryOrderDto toDeliveryOrderDto(DeliveryOrder order);
}
