package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.StaffCoverDto;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.BookingStatus;
import com.ifanmorgan.restaurant.entities.RestaurantTable;
import com.ifanmorgan.restaurant.entities.TimeSlot;
import com.ifanmorgan.restaurant.exceptions.*;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.TableRepository;
import com.ifanmorgan.restaurant.repositories.TimeSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingMapper bookingMapper;
    private final TimeSlotRepository timeSlotRepository;
    private final TableRepository tableRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;

    public BookingDto createBooking(LocalDate bookingDate, LocalTime bookingTime, Integer guests, Long customerId) {
        var timeSlot = timeSlotRepository.findByStartTime(bookingTime).orElse(null);
        if (timeSlot == null) {
            throw new TimeSlotNotFoundException();
        }

        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        var table = tableRepository.findById(1L).orElseThrow();
        var booking = new Booking();
        booking.setBookingDate(bookingDate);
        booking.setBookingTime(bookingTime);
        booking.setGuests(guests);
        booking.setTable(table);
        booking.setCustomer(customer);
        booking.setTimeSlot(timeSlot);

        bookingRepository.save(booking);
        return bookingMapper.toDto(booking);

    }

//    public List<RestaurantTable> getAvailableTables(LocalDate bookingDate, LocalTime startTime) {
//        var tables =  seatingRepository.findAvailableTables(bookingDate, startTime).orElse(null);
//        if (tables == null) {
//            throw new TablesNotFoundException();
//        }
//        return tables;
//    }

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
