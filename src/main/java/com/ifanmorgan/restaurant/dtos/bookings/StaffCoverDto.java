package com.ifanmorgan.restaurant.dtos.bookings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffCoverDto {
    private int numberOfWaiters;
    private int numberOfChefs;
}
