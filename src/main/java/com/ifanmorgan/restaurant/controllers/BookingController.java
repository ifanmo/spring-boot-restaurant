package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.dtos.GetAvailableTablesRequest;
import com.ifanmorgan.restaurant.dtos.StaffCoverDto;
import com.ifanmorgan.restaurant.entities.Seating;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.services.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return bookingService.getAvailableTables(request.getBookingDate(), request.getStartTime());
    }

    @GetMapping("staff-cover")
    public ResponseEntity<StaffCoverDto> getStaffCover() {
        var staffCoverDto = bookingService.getStaffCover();
        return new ResponseEntity<>(staffCoverDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(
            @Valid @RequestBody CreateBookingRequest request) {

        return bookingService.createBooking(request);
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
