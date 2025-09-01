package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.RestaurantTable;
import com.ifanmorgan.restaurant.entities.TableStatus;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.RestaurantTableRepository;
import com.ifanmorgan.restaurant.services.BookingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingMapper bookingMapper;
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(
            @RequestBody CreateBookingRequest requestDto,
            UriComponentsBuilder uriBuilder
    ) {

        var booking = bookingService.createBooking(requestDto);

        var bookingDto = bookingMapper.toDto(booking);
        var uri = uriBuilder.path("/bookings/{id}").buildAndExpand(booking.getId()).toUri();
        return ResponseEntity.created(uri).body(bookingDto);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<BookingDto> updateBooking(
            @PathVariable(name = "id") Long id
    ) {
       return bookingService.updateBooking(id);
    }

}
