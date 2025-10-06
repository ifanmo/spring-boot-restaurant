package com.ifanmorgan.restaurant.users.staff;

import com.ifanmorgan.restaurant.auth.AuthService;
import com.ifanmorgan.restaurant.users.Role;
import com.ifanmorgan.restaurant.users.UserNotFoundException;
import com.ifanmorgan.restaurant.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final StaffMapper staffMapper;
    private final ShiftRepository shiftRepository;
    private final AuthService authService;

    public StaffDto createStaff(Staff staff) {

        var user = authService.getCurrentUser();

        staff.setUser(user);
        staffRepository.save(staff);
        return staffMapper.toDto(staff);
    }

    public StaffDto addShift(Long shiftId, Long id, LocalDate date) {
        var staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new StaffNotFoundException();
        }

        var shift = shiftRepository.findById(shiftId).orElse(null);
        if (shift == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shift not found");
        }

        staff.addShift(shift, date);

        staffRepository.save(staff);

        return staffMapper.toDto(staff);
    }

    public Staff me() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long)authentication.getPrincipal();

        var staff = staffRepository.findById(userId).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found");
        }

        return staff;
    }
}
