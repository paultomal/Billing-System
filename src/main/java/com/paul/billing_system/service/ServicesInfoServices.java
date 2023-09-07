package com.paul.billing_system.service;

import com.paul.billing_system.dto.ServicesInfoDTO;
import com.paul.billing_system.entity.Department;
import com.paul.billing_system.entity.ServicesInfo;
import com.paul.billing_system.repository.DepartmentRepository;
import com.paul.billing_system.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesInfoServices {
    private final ServicesRepository servicesRepository;
    private final DepartmentRepository departmentRepository;

    public ServicesInfoServices(ServicesRepository servicesRepository, DepartmentRepository departmentRepository) {
        this.servicesRepository = servicesRepository;
        this.departmentRepository = departmentRepository;
    }

    public ServicesInfo save(Long id, ServicesInfoDTO servicesInfoDTO) {
        Optional<Department> department = departmentRepository.findById(id);
        ServicesInfo servicesInfo = new ServicesInfo();
        if(department.isPresent()){
        servicesInfo.setServiceName(servicesInfoDTO.getServiceName());
        servicesInfo.setServiceCharge(servicesInfoDTO.getServiceCharge());
         servicesRepository.save(servicesInfo);
         department.get().setServices(List.of(servicesInfo));
         departmentRepository.save(department.get());
        }
        return servicesInfo;
    }

    public List<ServicesInfo> getAllServices() {
    return servicesRepository.findAll();
    }

    public ServicesInfo getServiceById(Long id) {
        Optional<ServicesInfo> servicesInfo = servicesRepository.findById(id);
        if (servicesInfo.isPresent()) {
            return servicesInfo.get();
        }
        return new ServicesInfo();
    }

    public ServicesInfo updateService(ServicesInfoDTO servicesInfoDTO, Long id) {
    Optional<ServicesInfo> servicesInfo = servicesRepository.findById(id);
        if (servicesInfo.isPresent()) {
            ServicesInfo servicesInfo1 = new ServicesInfo();
            servicesInfo1.setServiceName(servicesInfoDTO.getServiceName());
            servicesInfo1.setServiceCharge(servicesInfoDTO.getServiceCharge());
            return servicesRepository.save(servicesInfo1);
        }return new ServicesInfo();
    }
}
