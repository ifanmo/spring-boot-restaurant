package com.ifanmorgan.restaurant.mappers;

import com.ifanmorgan.restaurant.dtos.bookings.BookingDto;
import com.ifanmorgan.restaurant.dtos.bookings.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.bookings.Booking;
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
