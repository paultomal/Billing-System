package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Investigation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvestigationRepository extends JpaRepository<Investigation, Long> {
 /*    @Query("SELECT d FROM Doctors d WHERE d.organization.id = :id AND d.specialist.id = :sId")
    List<Doctors> findByOrganizationAndSpecialist(Long id, Long sId);*/

    @Query("SELECT i FROM  Investigation i WHERE  i.organization.id = :id AND i.specialist.id = :spId")
    List<Investigation> findByOrganizationAndSpecialist(Long id, Long spId, PageRequest pageRequest);


}
