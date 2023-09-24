package com.paul.billing_system.repository;

import com.paul.billing_system.entity.UserInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);
    //Optional<UserInfo> findByEmail(String username);

    @Query("SELECT a FROM UserInfo a where a.organization.id = :id")
    List<UserInfo> findByOrganization(Long id, PageRequest pageRequest);

    @Query("SELECT a FROM UserInfo a WHERE a.organization.id = :id AND a.specialist.id = :spId")
    List<UserInfo> findByOrganizationAndSpecialist(Long id, Long spId, PageRequest pageRequest);
}
