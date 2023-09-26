package com.paul.billing_system.repository;

import com.paul.billing_system.entity.DrugOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugOrderRepository extends JpaRepository<DrugOrder, Long> {
}
