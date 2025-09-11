package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.dtos.GetAvailableTablesRequest;
import com.ifanmorgan.restaurant.dtos.StaffCoverDto;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.BookingStatus;
import com.ifanmorgan.restaurant.entities.RestaurantTable;
import com.ifanmorgan.restaurant.exceptions.*;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.TableRepository;
import com.ifanmorgan.restaurant.repositories.TimeSlotRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingMapper bookingMapper;
    private final TimeSlotRepository timeSlotRepository;
    private final TableRepository tableRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public BookingDto createBooking(CreateBookingRequest request) {
        final long DEFAULT_DURATION_IN_HOURS = 1;
        var startTime = request.getBookingTime();
        var customerId = request.getCustomerId();
        var endTime = startTime.plusHours(DEFAULT_DURATION_IN_HOURS);
        if (request.getBookingExtension() != null) {
           endTime = endTime.plusMinutes(request.getBookingExtension());
        }
        var bookingDate = request.getBookingDate();
        var guests = request.getGuests();

        var timeSlot = timeSlotRepository.findByStartTime(startTime).orElse(null);
        if (timeSlot == null) {
            throw new TimeSlotNotFoundException();
        }
        var customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var table = tableRepository.findTableForBooking(bookingDate, startTime, endTime, guests).orElse(null);
        if (table == null) {
            throw new TableNotAvailableException();
        }
        var booking = new Booking();
        booking.setBookingDate(bookingDate);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setGuests(guests);
        booking.setTable(table);
        booking.setCustomer(customer);
        booking.setTimeSlot(timeSlot);

        bookingRepository.save(booking);
        return bookingMapper.toDto(booking);

    }

    @Transactional
    public List<RestaurantTable> getAvailableTables(GetAvailableTablesRequest request) {
        final long DEFAULT_DURATION_IN_HOURS = 1;
        var bookingDate = request.getBookingDate();
        var startTime = request.getStartTime();
        var endTime = startTime.plusHours(DEFAULT_DURATION_IN_HOURS);

        if (request.getBookingExtension() != null) {
            endTime = endTime.plusMinutes(request.getBookingExtension());
        }

        System.out.println(endTime);

        var tables = tableRepository.findAvailableTables(startTime ,endTime, bookingDate).orElse(null);
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
