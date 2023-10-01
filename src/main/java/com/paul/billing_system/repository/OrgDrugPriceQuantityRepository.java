package com.paul.billing_system.repository;

import com.paul.billing_system.entity.OrgDrugPriceQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgDrugPriceQuantityRepository extends JpaRepository<OrgDrugPriceQuantity, Long> {
    OrgDrugPriceQuantity findByOrganizationIdAndDrugId(Long orgId, Long drugId);
}
