package com.paul.billing_system.repository;

import com.paul.billing_system.entity.Doctor;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Speciality;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("select d from Doctor d where d.name like concat('%', :name, '%') ")
    List<Doctor> findByName(@Param("name") String name, Pageable pageable);

    List<Doctor> findAllByOrganizationListContainsAndSpecialityListContains(Organization organization, Speciality speciality, Pageable pageable);

}
