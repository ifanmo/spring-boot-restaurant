package com.ifanmorgan.restaurant.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddShiftToStaffRequest {
    @NotNull(message = "Shift Id is required")
    private Long shiftId;
}
