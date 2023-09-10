package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findByType(OrganizationTypes type);
}
