package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patients, Long> {
}
