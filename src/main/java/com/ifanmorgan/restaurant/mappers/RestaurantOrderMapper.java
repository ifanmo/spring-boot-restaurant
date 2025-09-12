package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.RestaurantOrderDto;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.RestaurantOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantOrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    RestaurantOrderDto toDto(RestaurantOrder order);
}
