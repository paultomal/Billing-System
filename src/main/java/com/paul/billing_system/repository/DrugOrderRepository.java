package com.paul.billing_system.repository;

import com.paul.billing_system.entity.DrugOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugOrderRepository extends JpaRepository<DrugOrder, Long> {
    List<DrugOrder> getDrugOrdersByOrganizationId(Long id, Pageable pageable);
}
