package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.name like concat('%', :name, '%') and p.organization.id = :orgId")
    List<Patient> findByName(@Param("orgId") Long orgId, @Param("name") String name, Pageable pageable);

    @Query("SELECT p from  Patient p where p.organization.id = :id")
    List<Patient> findByOrganization(Long id, Pageable pageable);

}
