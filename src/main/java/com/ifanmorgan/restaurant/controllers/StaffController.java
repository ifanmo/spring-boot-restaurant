package com.ifanmorgan.restaurant.controllers;

import com.ifanmorgan.restaurant.dtos.CreateStaffProfileRequest;
import com.ifanmorgan.restaurant.dtos.StaffDto;
import com.ifanmorgan.restaurant.mappers.StaffMapper;
import com.ifanmorgan.restaurant.services.StaffService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
