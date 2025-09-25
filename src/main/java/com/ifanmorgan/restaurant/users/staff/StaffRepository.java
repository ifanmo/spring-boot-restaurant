package com.ifanmorgan.restaurant.users.staff;

import com.ifanmorgan.restaurant.users.customers.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT s, COUNT(sh) FROM Staff s JOIN s.shifts sh GROUP BY s ORDER BY COUNT(sh) desc")
    List<Staff> findMostActiveStaff(Pageable pageable);
}