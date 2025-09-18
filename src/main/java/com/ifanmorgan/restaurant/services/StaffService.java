package com.ifanmorgan.restaurant.services;

import com.ifanmorgan.restaurant.dtos.StaffDto;
import com.ifanmorgan.restaurant.entities.users.Role;
import com.ifanmorgan.restaurant.entities.users.Staff;
import com.ifanmorgan.restaurant.mappers.StaffMapper;
import com.ifanmorgan.restaurant.repositories.ShiftRepository;
import com.ifanmorgan.restaurant.repositories.StaffRepository;
import com.ifanmorgan.restaurant.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final StaffMapper staffMapper;
    private final ShiftRepository shiftRepository;
    public StaffDto createStaff(Staff staff) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long)authentication.getPrincipal();
        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (user.getRole().equals(Role.CUSTOMER)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only Staff can create staff profiles");
        }

        staff.setUser(user);
        staffRepository.save(staff);
        return staffMapper.toDto(staff);
    }

    public void addShift(Long shiftId, Long id) {
        var staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found");
        }

        var shift = shiftRepository.findById(shiftId).orElse(null);
        if (shift == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift not found");
        }

        staff.addShift(shift);
        staffRepository.save(staff);
    }
}
