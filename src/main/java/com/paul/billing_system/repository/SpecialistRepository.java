package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialistRepository extends JpaRepository<Specialist,Long> {

    @Query("select s from Specialist s where s.medSpecName like concat( '%',:medSpecName, '%') ")
    List<Specialist> findBySearch(@Param("medSpecName") String medSpecName);

    //@Query("SELECT s FROM Specialist s WHERE s.medSpecName IN :medSpecName")
    @Query("select s from Specialist s where s.medSpecName IN :medSpecName")
    List<Specialist> findByMedSpecName(@Param("medSpecName") List<String> medSpecName);

}
