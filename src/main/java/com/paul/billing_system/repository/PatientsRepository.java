package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientsRepository extends JpaRepository<Patients, Long> {

    @Query("select p from Patients p where p.name like concat('%', :name, '%') ")
    List<Patients> findByName(@Param("name") String name);
}
