package com.paul.billing_system.repository;

import com.paul.billing_system.entity.DrugVendor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrugVendorRepository extends JpaRepository<DrugVendor, Long> {
    DrugVendor findByName(String name);

    @Query("select d from DrugVendor d where d.name like concat('%', :name, '%') ")
    List<DrugVendor> searchByName(@Param("name") String name, Pageable pageable);
}
