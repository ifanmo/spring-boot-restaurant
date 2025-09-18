package com.ifanmorgan.restaurant.entities.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "date")
    private LocalDate date;

    @ManyToMany(mappedBy = "shifts")
    private Set<Staff> staff = new LinkedHashSet<>();

    public Integer calculateDuration() {
        return Math.toIntExact(Duration.between(startTime, endTime).toHours());
    }

}