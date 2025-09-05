package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "customer.id", source = "customerId")
    Booking toEntity(CreateBookingRequest request);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "tableId", source = "table.id")
    @Mapping(target = "bookingTime", source = "startTime")
    BookingDto toDto(Booking booking);
}
