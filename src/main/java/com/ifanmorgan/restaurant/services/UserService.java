package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.entities.Booking;
import com.ifanmorgan.restaurant.entities.BookingStatus;
import com.ifanmorgan.restaurant.entities.Customer;
import com.ifanmorgan.restaurant.entities.User;
import com.ifanmorgan.restaurant.repositories.BookingRepository;
import com.ifanmorgan.restaurant.repositories.CustomerRepository;
import com.ifanmorgan.restaurant.repositories.RestaurantTableRepository;
import com.ifanmorgan.restaurant.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Service
public class UserService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private UserRepository userRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showEntityStates() {
        var user = new User();
        user.setEmail("user1@emample.com");
        user.setPassword("password");

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient/Detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient/Detached");
        }
    }

    public void showRelatedEntities() {
        var user = userRepository.findById(2L).orElseThrow();
        System.out.println(user);
    }

    public void fetchBooking() {
        bookingRepository.findById(1L).orElseThrow();
    }

    @Transactional
    public void persistRelatedEntities() {
        var user = userRepository.findById(4L).orElseThrow();
        var customer = new Customer();
        customer.setUser(user);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setAddress("Neverland");
        customer.setPostcode("12345");

        var table = restaurantTableRepository.findById(6L).orElseThrow();
        var booking = new Booking();
        booking.setNumberOfGuests(2);
        booking.setStartTime(LocalTime.MIDNIGHT);
        booking.setDate(LocalDate.now());
        booking.setStatus("PENDING");
        booking.addTable(table);

        customer.addBooking(booking);
        customerRepository.save(customer);

    }
}
