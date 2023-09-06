package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctors, Long> {
}
