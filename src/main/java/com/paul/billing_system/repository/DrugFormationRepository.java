package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Drug;
import com.paul.billing_system.entity.DrugFormation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugFormationRepository extends JpaRepository<DrugFormation, Long> {
    DrugFormation findByName(String name);

    @Query("select d from DrugFormation d where d.name like concat('%', :name, '%') ")
    List<DrugFormation> searchByName(@Param("name") String name, Pageable pageable);
}
