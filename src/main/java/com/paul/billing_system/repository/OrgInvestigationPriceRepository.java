package com.paul.billing_system.repository;

import com.paul.billing_system.entity.OrgInvestigationPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrgInvestigationPriceRepository extends JpaRepository<OrgInvestigationPrice, Long> {


    @Query("select obi from OrgInvestigationPrice obi where obi.organization.id = :orgId and obi.investigation.id = :inId")
    OrgInvestigationPrice findByOrganizationAndInvestigation(Long orgId, Long inId);
}
