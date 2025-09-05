package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.dtos.StaffCoverDto;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.BookingStatus;
import com.ifanmorgan.restaurant.entities.Seating;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.SeatingRepository;
import com.ifanmorgan.restaurant.repositories.TimeSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingMapper bookingMapper;
    private final TimeSlotRepository timeSlotRepository;
    private final SeatingRepository seatingRepository;
    private final BookingRepository bookingRepository;

    public ResponseEntity<BookingDto> createBooking(CreateBookingRequest request) {
        var timeSlot = timeSlotRepository.findByStartTime(request.getStartTime()).orElse(null);
        if (timeSlot == null) {
            return ResponseEntity.notFound().build();
        }
        var table = seatingRepository.findAvailableTableForBooking(
                request.getBookingDate(),
                request.getStartTime(),
                request.getGuests())
                .orElse(null);

        if (table == null) {
            return ResponseEntity.badRequest().build();
        }
        var booking = bookingMapper.toEntity(request);
        booking.setBookingTimeSlot(timeSlot);
        booking.setTable(table);

        bookingRepository.save(booking);
        var bookingDto = bookingMapper.toDto(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);

    }

    public ResponseEntity<List<Seating>> getAvailableTables(LocalDate bookingDate, LocalTime startTime) {
        var tables = seatingRepository.findAvailableTables(bookingDate, startTime).orElse(null);
        return ResponseEntity.ok(tables);
    }

    public ResponseEntity<Void> approveBooking(Long id) {
        var booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        if (booking.getStatus() == BookingStatus.PENDING) {
            booking.setStatus(BookingStatus.APPROVED);
        }
        bookingRepository.save(booking);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteBooking(Long id) {
        var booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        bookingRepository.delete(booking);
        return ResponseEntity.noContent().build();
    }

    public StaffCoverDto getStaffCover() {

        // 2 Chefs Required Per 10 Customers
        var numberOfChefs = bookingRepository.getNumberOfGuests() / 10 * 2;
        // 1 Waiter Required Per 2 Tables
        var numberOfWaiters = bookingRepository.getNumberOfTables() / 2;

        return new StaffCoverDto(numberOfWaiters, numberOfChefs);
    }
}
