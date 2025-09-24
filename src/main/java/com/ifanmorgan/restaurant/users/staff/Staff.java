package com.ifanmorgan.restaurant.users.staff;

import com.ifanmorgan.restaurant.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<StaffShift> shifts = new HashSet<>();

    public StaffShift getStaffShift(Long shiftId) {
        for (var shift : shifts) {
            if (shift.getShift().getId().equals(shiftId)) {
                return shift;
            }
        }
        return null;
    }

    public void addShift(Shift shift, LocalDate date) {
        var staffShift = new StaffShift();
        staffShift.setShift(shift);
        staffShift.setDate(date);
        staffShift.setStaff(this);
        this.shifts.add(staffShift);
    }

    public int calculateHoursWorked() {
        return shifts.stream()
                .map(StaffShift::getDuration)
                .reduce(0, Integer::sum);

    }

}