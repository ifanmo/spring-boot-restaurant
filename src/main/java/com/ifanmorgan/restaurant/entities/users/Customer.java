package com.ifanmorgan.restaurant.entities.users;

import com.ifanmorgan.restaurant.entities.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street_number")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "postcode")
    private String postcode;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

}