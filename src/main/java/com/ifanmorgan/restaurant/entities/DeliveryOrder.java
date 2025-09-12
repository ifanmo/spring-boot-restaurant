package com.ifanmorgan.restaurant.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "DELIVERY")
public class DeliveryOrder extends Order {
    @Column(name = "delivery_status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(name = "delivery_time")
    private LocalTime deliveryTime;

    @ManyToOne
    @JoinColumn(name = "driver")
    private Staff driver;

}