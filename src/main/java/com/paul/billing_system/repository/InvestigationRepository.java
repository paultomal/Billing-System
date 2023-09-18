package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Investigation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestigationRepository extends JpaRepository<Investigation, Long> {
}
