package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.entity.Organization;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctors, Long> {



    List<Doctors> findAllByOrganization(Organization organization , Pageable pageable);
    @Query("select d from Doctors d where d.name like concat('%', :name, '%') ")
    List<Doctors> findByName(@Param("name") String name, Pageable pageable);

}
