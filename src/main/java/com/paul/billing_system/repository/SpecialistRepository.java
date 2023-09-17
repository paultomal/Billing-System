package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistRepository extends JpaRepository<Specialist,Long> {
}
