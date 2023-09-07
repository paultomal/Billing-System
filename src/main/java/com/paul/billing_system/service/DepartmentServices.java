package com.paul.billing_system.service;

import com.paul.billing_system.dto.DepartmentDTO;
import com.paul.billing_system.entity.Department;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.ServicesInfo;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.repository.DepartmentRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServices {
    private final DepartmentRepository departmentRepository;
    private final OrganizationRepository organizationRepository;

    public DepartmentServices(DepartmentRepository departmentRepository, OrganizationRepository organizationRepository) {
        this.departmentRepository = departmentRepository;
        this.organizationRepository = organizationRepository;
    }

    public Department save(DepartmentDTO departmentDTO,Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Department department = new Department();
        if (organization.isPresent()){

            department.setDeptName(departmentDTO.getDeptName());
            department.setNoOfPatients(departmentDTO.getNoOfPatients());
            department.setServices(departmentDTO.getServices().stream().map(ServicesInfo::form).toList());
            //department = departmentRepository.save(department);
            Organization newOrg = organization.get();

            newOrg.setDepartments((List.of(department)));

            organizationRepository.save(newOrg);

        }


    return department;
    }

    public List<Department> getAllDepartmant() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
    Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        }return new Department();
    }

    public Department updateDepartment(DepartmentDTO departmentDTO, Long id) {
    Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            Department department1 = department.get();
            department1.setDeptName(departmentDTO.getDeptName());
            department1.setNoOfPatients(departmentDTO.getNoOfPatients());
            department1.setServices(departmentDTO.getServices().stream().map(ServicesInfo::form).toList());
            return departmentRepository.save(department1);
        }
        return new Department();
    }
}
