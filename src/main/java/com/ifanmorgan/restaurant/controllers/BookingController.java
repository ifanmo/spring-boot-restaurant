package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/bookings")
class BookingController {
    private final BookingMapper bookingMapper;

    @PostMapping
    public Booking createBooking(
            @RequestBody CreateBookingRequest request) {

        return bookingMapper.toEntity(request);
    }

}
