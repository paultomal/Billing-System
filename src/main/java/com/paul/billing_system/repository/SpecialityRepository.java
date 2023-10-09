package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Speciality;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

    @Query("select s from Speciality s where s.medSpecName like concat( '%',:medSpecName, '%') ")
    List<Speciality> findBySearch(@Param("medSpecName") String medSpecName, Pageable pageable);

    Optional<Speciality> findByMedSpecName(String medSpecName);
}








































/*
    //@Query("SELECT s FROM Specialist s WHERE s.medSpecName IN :medSpecName")
    @Query("select s from Specialist s where s.medSpecName IN :medSpecName")
    List<Specialist> findByMedSpecName(@Param("medSpecName") List<String> medSpecName);*/
