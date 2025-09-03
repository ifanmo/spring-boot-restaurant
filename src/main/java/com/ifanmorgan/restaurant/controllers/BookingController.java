package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/bookings")
class BookingController {
    private final BookingMapper bookingMapper;
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(
            @RequestBody CreateBookingRequest request) {

        var booking = bookingService.createBooking(request);

        var bookingDto = bookingMapper.toDto(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);

    }

}
