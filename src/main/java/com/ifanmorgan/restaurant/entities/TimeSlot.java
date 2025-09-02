package com.ifanmorgan.restaurant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "time_slot")
public class TimeSlot {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    //TODO [Reverse Engineering] generate columns from DB
}