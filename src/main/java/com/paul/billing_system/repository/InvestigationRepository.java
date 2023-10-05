package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Investigation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvestigationRepository extends JpaRepository<Investigation, Long> {


    @Query("select i from Investigation i where i.serviceName like concat('%', :name, '%') ")
    List<Investigation> searchByName(@Param("name") String name, Pageable pageable);

}
