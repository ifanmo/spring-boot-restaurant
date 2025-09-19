package com.ifanmorgan.restaurant.bookings;

import com.ifanmorgan.restaurant.misc.ErrorDto;
import com.ifanmorgan.restaurant.users.customers.CustomerNotFoundException;
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
    private final BookingService bookingService;

    @GetMapping("/available-tables")
    public ResponseEntity<List<RestaurantTable>> getAvailableTables(
            @Valid @RequestBody GetAvailableTablesRequest request
    ) {

        var tables = bookingService.getAvailableTables(request);
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/staff-cover")
    public ResponseEntity<StaffCoverDto> getStaffCover() {
        var staffCoverDto = bookingService.getStaffCover();
        return new ResponseEntity<>(staffCoverDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(
            @Valid @RequestBody CreateBookingRequest request) {
        var bookingDto = bookingService.createBooking(request);

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

    @ExceptionHandler({TimeSlotNotFoundException.class, BookingNotFoundException.class, CustomerNotFoundException.class})
    public ResponseEntity<ErrorDto> handleNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(TableNotAvailableException.class)
    public ResponseEntity<ErrorDto> handleBadRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }

}
