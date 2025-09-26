package com.ifanmorgan.restaurant.bookings.mappers;

import com.ifanmorgan.restaurant.bookings.dtos.BookingDto;
import com.ifanmorgan.restaurant.bookings.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.bookings.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toEntity(CreateBookingRequest request);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "tableId", source = "table.id")
    @Mapping(target = "bookingTime", source = "startTime")
    BookingDto toDto(Booking booking);
}
