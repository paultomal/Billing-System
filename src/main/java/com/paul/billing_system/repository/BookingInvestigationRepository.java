package com.paul.billing_system.repository;

import com.paul.billing_system.entity.BookingInvestigation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingInvestigationRepository extends JpaRepository<BookingInvestigation, Long> {
}
