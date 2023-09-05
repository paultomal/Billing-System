package com.paul.billing_system.repository;

import com.paul.billing_system.entity.ServicesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicesRepository extends JpaRepository<ServicesInfo, Long> {
}
