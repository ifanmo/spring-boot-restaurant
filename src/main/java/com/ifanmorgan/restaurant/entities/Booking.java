package com.ifanmorgan.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "seating_id")
    private Seating table;

    @ManyToOne
    @JoinColumn(name = "booking_time_slot")
    private TimeSlot bookingTimeSlot;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "guests")
    private Integer guests;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    @Column(name = "created_at")
    private Instant createdAt;

}