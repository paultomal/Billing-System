package com.paul.billing_system.repository;

import com.paul.billing_system.entity.DoctorSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorSlotRepository extends JpaRepository<DoctorSlot, Long> {
}
