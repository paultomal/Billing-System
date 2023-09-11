package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Department;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.ServicesInfo;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;

    @NotEmpty(message = "Department name should not be empty")
    private String deptName;

    private Long noOfPatients;

    private List<ServicesInfoDTO> services;


    public static DepartmentDTO form(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setDeptName(department.getDeptName());
        departmentDTO.setNoOfPatients(department.getNoOfPatients());
        departmentDTO.setServices(department.getServices().stream().map(ServicesInfoDTO::form).toList());
        return departmentDTO;
    }
}