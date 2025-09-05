package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.StaffCoverDto;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.BookingStatus;
import com.ifanmorgan.restaurant.entities.Seating;
import com.ifanmorgan.restaurant.exceptions.*;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.SeatingRepository;
import com.ifanmorgan.restaurant.repositories.TimeSlotRepository;
import lombok.AllArgsConstructor;
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
    private final CustomerRepository customerRepository;

    public BookingDto createBooking(LocalDate bookingDate, LocalTime startTime, Integer guests, Long customerId) {
        var timeSlot = timeSlotRepository.findByStartTime(startTime).orElse(null);
        if (timeSlot == null) {
            throw new TimeSlotNotFoundException();
        }
        var table = seatingRepository.findAvailableTableForBooking(bookingDate, startTime, guests).orElse(null);

        if (table == null) {
            throw new TableNotAvailableException();
        }

        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var booking = new Booking();
        booking.setBookingTimeSlot(timeSlot);
        booking.setBookingDate(bookingDate);
        booking.setGuests(guests);
        booking.setTable(table);
        booking.setCustomer(customer);

        bookingRepository.save(booking);
        return bookingMapper.toDto(booking);

    }

    public List<Seating> getAvailableTables(LocalDate bookingDate, LocalTime startTime) {
        var tables =  seatingRepository.findAvailableTables(bookingDate, startTime).orElse(null);
        if (tables == null) {
            throw new TablesNotFoundException();
        }
        return tables;
    }

    public void approveBooking(Long id) {
        var booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            throw new BookingNotFoundException();
        }

        if (booking.getStatus() == BookingStatus.PENDING) {
            booking.setStatus(BookingStatus.APPROVED);
        }
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        var booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            throw new BookingNotFoundException();
        }

        bookingRepository.delete(booking);

    }

    public StaffCoverDto getStaffCover() {

        // 2 Chefs Required Per 10 Customers
        var numberOfChefs = bookingRepository.getNumberOfGuests() / 10 * 2;
        // 1 Waiter Required Per 2 Tables
        var numberOfWaiters = bookingRepository.getNumberOfTables() / 2;

        return new StaffCoverDto(numberOfWaiters, numberOfChefs);
    }
}
