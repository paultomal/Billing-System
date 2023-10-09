package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Generic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenericRepository extends JpaRepository<Generic, Long> {
    Generic findByName(String name);

    @Query("select g from Generic g where g.name like concat('%', :name, '%') ")
    List<Generic> searchByName(@Param("name") String name, Pageable pageable);
}
