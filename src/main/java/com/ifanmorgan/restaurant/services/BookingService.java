package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.BookingDto;
import com.ifanmorgan.restaurant.dtos.CreateBookingRequest;
import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.BookingStatus;
import com.ifanmorgan.restaurant.entities.RestaurantTable;
import com.ifanmorgan.restaurant.entities.TableStatus;
import com.ifanmorgan.restaurant.mappers.BookingMapper;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.RestaurantTableRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    NotificationHandler notificationHandler;
    RestaurantTableRepository restaurantTableRepository;

    @Transactional
    public Booking createBooking(CreateBookingRequest requestDto) {
        var booking = bookingMapper.toEntity(requestDto);
        var table =  restaurantTableRepository.findById(7L).orElseThrow();
        table.setStatus(TableStatus.BOOKED);
        booking.setRestaurantTable(table);
        return bookingRepository.save(booking);
    }

    public ResponseEntity<BookingDto> updateBooking(Long bookingId) {
        var booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        if (booking.getStatus().equals(BookingStatus.APPROVED)) {
            return ResponseEntity.badRequest().body(bookingMapper.toDto(booking));
        }

        booking.setStatus(BookingStatus.APPROVED);
        bookingRepository.save(booking);

        notificationHandler.handle("Your booking at " +  booking.getStartTime() + " has been approved.");


        return ResponseEntity.ok(bookingMapper.toDto(booking));
    }

    public ResponseEntity<BookingDto> cancelBooking(Long id) {
        var booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        if (booking.getStatus().equals(BookingStatus.CANCELLED)) {
            return ResponseEntity.badRequest().body(bookingMapper.toDto(booking));
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        notificationHandler.handle("Your booking at " +  booking.getStartTime() + " has been cancelled.");


        return ResponseEntity.ok(bookingMapper.toDto(booking));
    }
}
