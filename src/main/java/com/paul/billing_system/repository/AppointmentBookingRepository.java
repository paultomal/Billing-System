package com.paul.billing_system.repository;

import com.paul.billing_system.entity.AppointmentBooking;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentBookingRepository extends JpaRepository<AppointmentBooking, Long> {
    List<AppointmentBooking> findAllByOrganizationId(Long orgId, Pageable pageable);

    List<AppointmentBooking> findAllByOrganizationId(Long orgId);

    @Query("SELECT ab FROM AppointmentBooking ab WHERE ab.organization.id = :orgId AND ab.patient.name like concat( '%', :patientName, '%')")
    List<AppointmentBooking> findByOrganizationAndPatient(@Param("orgId") Long orgId, @Param("patientName") String patientName, PageRequest pageRequest);

    @Query("SELECT ab FROM AppointmentBooking ab WHERE ab.doctor.id = :doctorId AND ab.patient.id = :patientId")
    List<AppointmentBooking> findAllByDoctorIdAndPatientId(@Param("doctorId")Long doctorId, @Param("patientId")Long patientId);
}
