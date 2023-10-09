package com.paul.billing_system.repository;

import com.paul.billing_system.entity.InvestigationBooking;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvestigationBookingRepository extends JpaRepository<InvestigationBooking, Long> {

    @Query("SELECT ib FROM InvestigationBooking ib WHERE ib.organization.id = :orgId AND ib.patient.name like concat( '%',:patient, '%')")
    List<InvestigationBooking> findByOrganizationAndPatient(@Param("orgId") Long orgId, @Param("patient") String patient, PageRequest pageRequest);

    @Query("SELECT ib FROM InvestigationBooking ib WHERE ib.organization.id = :orId")
    List<InvestigationBooking> findByOrganization(Long orId, Pageable pageable);

    List<InvestigationBooking> findAllByOrganizationId(Long orgId);

}
