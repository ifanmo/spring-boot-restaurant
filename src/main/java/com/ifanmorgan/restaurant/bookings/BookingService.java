package com.ifanmorgan.restaurant.bookings;

import com.ifanmorgan.restaurant.auth.AuthService;
import com.ifanmorgan.restaurant.users.customers.CustomerNotFoundException;
import com.ifanmorgan.restaurant.users.customers.CustomerRepository;
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
    private final AuthService authService;

    @Transactional
    public BookingDto createBooking(CreateBookingRequest request) {
        final long DEFAULT_DURATION_IN_HOURS = 1;

        var user = authService.getCurrentUser();
        var customer = customerRepository.findById(user.getId()).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        var startTime = request.getBookingTime();
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
