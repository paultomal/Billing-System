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

    public Department save(Long id, DepartmentDTO departmentDTO) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Department department = new Department();
        if (organization.isPresent()){
            if (organization.get().getType().equals(OrganizationTypes.HOSPITAL)) {
                department.setDeptName(departmentDTO.getDeptName());
                department.setNoOfPatients(departmentDTO.getNoOfPatients());
                departmentRepository.save(department);
                organization.get().getDepartments().add(department);
                organizationRepository.save(organization.get());
            }
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
           //department1.setServices(departmentDTO.getServices().stream().map(ServicesInfo::form).toList());
            return departmentRepository.save(department1);
        }
        return new Department();
    }
}
