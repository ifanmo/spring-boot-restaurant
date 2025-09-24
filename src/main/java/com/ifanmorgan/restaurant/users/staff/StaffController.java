package com.ifanmorgan.restaurant.users.staff;

import com.ifanmorgan.restaurant.misc.ErrorDto;
import com.ifanmorgan.restaurant.users.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("staff")
@AllArgsConstructor
public class StaffController {
    private final StaffService staffService;
    private final StaffMapper staffMapper;

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'DELIVERY_DRIVER')")
    @PostMapping
    public ResponseEntity<StaffDto> createProfile(
            @Valid @RequestBody CreateStaffProfileRequest request
    ) {
        var staff = staffMapper.toEntity(request);
        var staffDto = staffService.createStaff(staff);

        return ResponseEntity.status(HttpStatus.CREATED).body(staffDto);

    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("{id}/shifts")
    public ResponseEntity<StaffDto> addShift(
            @PathVariable Long id,
            @Valid @RequestBody AddShiftToStaffRequest request
    ) {
         var staffDto = staffService.addShift(request.getShiftId(), id, request.getDate());
         return ResponseEntity.status(HttpStatus.CREATED).body(staffDto);
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'DELIVERY_DRIVER', 'MANAGER')")
    @GetMapping("/me")
    public ResponseEntity<StaffDto> me() {
        var staff = staffService.me();

        var staffDto = staffMapper.toDto(staff);

        return ResponseEntity.ok(staffDto);
    }

}
