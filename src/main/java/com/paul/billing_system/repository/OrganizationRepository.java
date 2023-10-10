package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findByType(OrganizationTypes type, Pageable pageable);

    List<Organization> findByType(OrganizationTypes type);

    Optional<Organization> findByOrgCode(String orgCode);

    Optional<Organization> findByEmail(String email);

    @Query("select o from Organization o where o.name like concat('%', :name, '%') and o.type = :type")
    List<Organization> searchByName(@Param("type") OrganizationTypes type, @Param("name") String name, Pageable pageable);

}
