package com.paul.billing_system.repository;

import com.paul.billing_system.entity.OrgBasedInvestigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrgBasedInvestigationRepository extends JpaRepository<OrgBasedInvestigation, Long> {

    /*    @Query("SELECT p from  Patient p where p.organization.id = :id")
    List<Patient> findByOrganization(Long id, Pageable pageable);*/

    @Query("select obi from OrgBasedInvestigation obi where obi.organization.id = :id")
    List<OrgBasedInvestigation> findByOrganization(Long id);
}
