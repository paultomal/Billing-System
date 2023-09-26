package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Drug;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    @Query("select d from Drug d where d.brandName like concat('%', :name, '%') ")
    List<Drug> searchByName(@Param("name") String name, Pageable pageable);

    @Query("select d from Drug d where d.vendor.name like concat('%', :name, '%') ")
    List<Drug> searchByVendor(@Param("name") String name, Pageable pageable);

    @Query("select d from Drug d where d.generic.name like concat('%', :name, '%') ")
    List<Drug> searchByGeneric(@Param("name") String name, Pageable pageable);

    @Query("select d from Drug d where d.formation.name like concat('%', :name, '%') ")
    List<Drug> searchByFormation(@Param("name") String name, Pageable pageable);

    @Query("select d from Drug d where d.strength.name like concat('%', :name, '%') ")
    List<Drug> searchByStrength(@Param("name") String name, Pageable pageable);
}
