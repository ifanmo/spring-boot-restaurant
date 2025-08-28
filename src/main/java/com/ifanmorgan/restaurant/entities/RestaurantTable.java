package com.ifanmorgan.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "restaurant_tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Booking booking;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TableStatus status;

}