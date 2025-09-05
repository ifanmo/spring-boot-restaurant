package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.dtos.GetAvailableTablesRequest;
import com.ifanmorgan.restaurant.dtos.StaffCoverDto;
import com.ifanmorgan.restaurant.entities.RestaurantTable;
import com.ifanmorgan.restaurant.exceptions.*;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.services.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
class BookingController {
    private final BookingMapper bookingMapper;
    private final BookingService bookingService;

    @GetMapping("/available-tables")
    public ResponseEntity<List<RestaurantTable>> getAvailableTables(
            @Valid @RequestBody GetAvailableTablesRequest request
    ) {
        var endTime = request.getStartTime().plusHours(1);
        var tables = bookingService.getAvailableTables(request.getBookingDate(), request.getStartTime(), endTime);
        return ResponseEntity.ok(tables);
    }

    @GetMapping("staff-cover")
    public ResponseEntity<StaffCoverDto> getStaffCover() {
        var staffCoverDto = bookingService.getStaffCover();
        return new ResponseEntity<>(staffCoverDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(
            @Valid @RequestBody CreateBookingRequest request) {
        var bookingDto = bookingService.createBooking(
                request.getBookingDate(),
                request.getBookingTime(),
                request.getGuests(),
                request.getCustomerId());

        return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/approve-booking")
    public ResponseEntity<Void> approveBooking(@PathVariable Long id) {
        bookingService.approveBooking(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(TimeSlotNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTimeSlotNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "TimeSlot not found"));
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBookingNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Booking not found"));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Customer not found"));
    }

    @ExceptionHandler(TablesNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTablesNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Tables not found"));
    }

    @ExceptionHandler(TableNotAvailableException.class)
    public ResponseEntity<Map<String, String>> handleTableNotAvailable() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No tables available for this time"));
    }

}
