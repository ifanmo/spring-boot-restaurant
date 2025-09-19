package com.ifanmorgan.restaurant.bookings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffCoverDto {
    private int numberOfWaiters;
    private int numberOfChefs;
}
