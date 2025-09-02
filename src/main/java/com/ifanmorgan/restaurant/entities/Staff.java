package com.ifanmorgan.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private User users;

    @Column(name = "first_name", nullable = false, length = 55)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 55)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "staff_shifts",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id"))
    private Set<Shift> shifts = new LinkedHashSet<>();

}