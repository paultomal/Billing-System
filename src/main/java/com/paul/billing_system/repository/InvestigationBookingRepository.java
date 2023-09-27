package com.paul.billing_system.repository;

import com.paul.billing_system.entity.InvestigationBooking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvestigationBookingRepository extends JpaRepository<InvestigationBooking, Long> {
    @Query("SELECT ib FROM InvestigationBooking ib WHERE ib.organization.id = :orId")
    List<InvestigationBooking> findByOrganization(Long orId, Pageable pageable);
}
