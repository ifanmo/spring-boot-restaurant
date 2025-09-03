package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.dtos.StaffCoverDto;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.BookingStatus;
import com.ifanmorgan.restaurant.entities.Seating;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.SeatingRepository;
import com.ifanmorgan.restaurant.repositories.TimeSlotRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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

    public Booking createBooking(CreateBookingRequest request) {
        var timeSlot = timeSlotRepository.findByStartTime(request.getStartTime()).orElseThrow();
        var table = getAvailableTableForBooking(request.getBookingDate(), request.getStartTime(), request.getGuests());
        var booking = bookingMapper.toEntity(request);
        booking.setBookingTimeSlot(timeSlot);
        booking.setTable(table);

        return bookingRepository.save(booking);

    }

    public List<Seating> getAvailableTables(LocalDate bookingDate, LocalTime startTime) {
        var tables = seatingRepository.findAvailableTables(bookingDate, startTime).orElse(null);
        if(tables == null) {
            throw new EntityNotFoundException("No available tables found for booking date: " + bookingDate);
        }
        return tables;
    }

    public Seating getAvailableTableForBooking(LocalDate bookingDate, LocalTime startTime, Integer guests) {
        var table = seatingRepository.findAvailableTableForBooking(bookingDate, startTime, guests).orElse(null);
        if(table == null) {
            throw new EntityNotFoundException("No available table found for booking date: " + bookingDate);
        }

        return table;
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
