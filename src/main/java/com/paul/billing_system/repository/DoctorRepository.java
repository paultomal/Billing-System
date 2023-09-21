package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctors, Long> {

    @Query("SELECT d FROM Doctors d WHERE d.organization.id = :id AND d.specialist.id = :sId")
    List<Doctors> findByOrganizationAndSpecialist(Long id, Long sId);
}
