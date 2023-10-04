package com.paul.billing_system.repository;

import com.paul.billing_system.entity.DrugOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugOrderRepository extends JpaRepository<DrugOrder, Long> {
    List<DrugOrder> getDrugOrdersByOrganizationId(Long id, Pageable pageable);

    @Query("SELECT do FROM DrugOrder do WHERE do.organization.id = :orgId AND do.patient.name like concat( '%', :patientName, '%')")
    List<DrugOrder> findByOrganizationAndPatient(@Param("orgId") Long orgId, @Param("patientName") String patientName, PageRequest pageRequest);
}
