package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.dtos.GetAvailableTablesRequest;
import com.ifanmorgan.restaurant.dtos.StaffCoverDto;
import com.ifanmorgan.restaurant.entities.Seating;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.services.BookingService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
class BookingController {
    private final BookingMapper bookingMapper;
    private final BookingService bookingService;

    @GetMapping("/available-tables")
    public ResponseEntity<List<Seating>> getAvailableTables(
            @RequestBody GetAvailableTablesRequest request
    ) {
        var tables = bookingService.getAvailableTables(request.getBookingDate(), request.getStartTime());
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @GetMapping("staff-cover")
    public ResponseEntity<StaffCoverDto> getStaffCover() {
        var staffCoverDto = bookingService.getStaffCover();
        return new ResponseEntity<>(staffCoverDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(
            @RequestBody CreateBookingRequest request) {

        var booking = bookingService.createBooking(request);

        var bookingDto = bookingMapper.toDto(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);

    }

    @PostMapping("/{id}/approve-booking")
    public ResponseEntity<Void> approveBooking(@PathVariable Long id) {
        return bookingService.approveBooking(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        return bookingService.deleteBooking(id);
    }



}
