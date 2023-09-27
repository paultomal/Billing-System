package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findByType(OrganizationTypes type, Pageable pageable);

    @Query("select o from Organization o where o.name like concat('%', :name, '%') ")
    List<Organization> searchByName(@Param("name") String name, Pageable pageable);

}
