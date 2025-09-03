package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.SeatingRepository;
import com.ifanmorgan.restaurant.repositories.TimeSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingMapper bookingMapper;
    private final TimeSlotRepository timeSlotRepository;
    private final SeatingRepository seatingRepository;
    private final BookingRepository bookingRepository;

    public Booking createBooking(CreateBookingRequest request) {
        var timeSlot = timeSlotRepository.findByStartTime(request.getStartTime()).orElseThrow();
        var table = seatingRepository.findById(1L).orElseThrow();
        var booking = bookingMapper.toEntity(request);
        System.out.println(booking.toString());
        booking.setBookingTimeSlot(timeSlot);
        booking.setTable(table);

        return bookingRepository.save(booking);

    }
}
