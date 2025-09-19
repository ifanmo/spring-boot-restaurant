package com.ifanmorgan.restaurant.users.staff;

import com.ifanmorgan.restaurant.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToMany
    @JoinTable(name = "staff_shifts",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id"))
    private Set<Shift> shifts = new HashSet<>();

    public void addShift(Shift shift) {
        this.shifts.add(shift);
    }

    public int calculateHoursWorked() {
        return shifts.stream()
                .map(Shift::calculateDuration)
                .reduce(0, Integer::sum);

    }

}