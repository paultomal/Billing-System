package com.paul.billing_system.repository;

import com.paul.billing_system.entity.OrgInvestigationPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrgInvestigationPriceRepository extends JpaRepository<OrgInvestigationPrice, Long> {

    /*    @Query("SELECT p from  Patient p where p.organization.id = :id")
    List<Patient> findByOrganization(Long id, Pageable pageable);*/

    @Query("select obi from OrgInvestigationPrice obi where obi.organization.id = :orgId and obi.investigation.id = :inId")
    OrgInvestigationPrice findByOrganizationAndInvestigation(Long orgId,Long inId);
}
