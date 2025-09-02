package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "bookingTimeSlot.id", source = "bookingTimeSlotId")
    Booking toEntity(CreateBookingRequest request);
}
