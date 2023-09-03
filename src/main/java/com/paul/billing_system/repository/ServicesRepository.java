package com.paul.billing_system.repository;

import com.paul.billing_system.entity.ServicesInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<ServicesInfo, Long> {
}
