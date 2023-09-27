package com.paul.billing_system.repository;

import com.paul.billing_system.entity.AppointmentBooking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentBookingRepository extends JpaRepository<AppointmentBooking, Long> {
    List<AppointmentBooking> findAllByOrganizationId(Long orgId, Pageable pageable);
}
