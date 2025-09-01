package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.RestaurantTable;
import com.ifanmorgan.restaurant.entities.TableStatus;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.RestaurantTableRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    RestaurantTableRepository restaurantTableRepository;

    @Transactional
    public Booking createBooking(CreateBookingRequest requestDto) {
        var booking = bookingMapper.toEntity(requestDto);
        var table =  restaurantTableRepository.findById(7L).orElseThrow();
        table.setStatus(TableStatus.BOOKED);
        booking.setRestaurantTable(table);
        return bookingRepository.save(booking);
    }
}
