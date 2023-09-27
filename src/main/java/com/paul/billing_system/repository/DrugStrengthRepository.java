package com.paul.billing_system.repository;

import com.paul.billing_system.entity.DrugStrength;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugStrengthRepository extends JpaRepository<DrugStrength, Long> {
    DrugStrength findByName(String name);
    @Query("select d from DrugStrength d where d.name like concat('%', :name, '%') ")
    List<DrugStrength> searchByName(@Param("name") String name, Pageable pageable);
}
