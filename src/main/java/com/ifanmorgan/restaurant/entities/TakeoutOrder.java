package com.ifanmorgan.restaurant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "TAKEOUT")
public class TakeoutOrder extends Order {
    @Column(name = "pickup_time")
    private LocalTime pickupTime;
}