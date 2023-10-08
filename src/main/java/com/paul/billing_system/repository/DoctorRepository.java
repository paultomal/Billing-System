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

    @Query("select d from Doctor d where d.name like concat('%', :name, '%')")
    List<Doctor> findByName(@Param("name") String name, Pageable pageable);

    @Query(value = "select * from doctor d join doctor_organization d_o on d.id = d_o.doctor_id and d_o.organization_id = :orgId where d.name like concat('%', :name, '%')", nativeQuery = true)
    List<Doctor> findDoctorByNameUnderOrg(@Param("orgId") Long orgId, @Param("name") String name, Pageable pageable);

    List<Doctor> findAllByOrganizationListContainsAndSpecialityListContains(Organization organization, Speciality speciality, Pageable pageable);
    List<Doctor> findAllByOrganizationListContainsAndSpecialityListContains(Organization organization, Speciality speciality);

}