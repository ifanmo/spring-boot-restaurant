package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.*;
import com.ifanmorgan.restaurant.mappers.StaffMapper;
import com.ifanmorgan.restaurant.services.StaffService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("staff")
@AllArgsConstructor
public class StaffController {
    private final StaffService staffService;
    private final StaffMapper staffMapper;

    @PostMapping
    public ResponseEntity<StaffDto> createProfile(
            @Valid @RequestBody CreateStaffProfileRequest request
    ) {
        var staff = staffMapper.toEntity(request);
        var staffDto = staffService.createStaff(staff);

        return ResponseEntity.status(HttpStatus.CREATED).body(staffDto);

    }

    @PostMapping("{id}/shifts")
    public ResponseEntity<Void> addShift(
            @PathVariable Long id,
            @Valid @RequestBody AddShiftToStaffRequest request
    ) {
         staffService.addShift(request.getShiftId(), id);
         return ResponseEntity.ok().build();
    }

}
