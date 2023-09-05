package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Department;
import com.paul.billing_system.entity.ServicesInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesInfoDTO {
    private Long id;

    private String serviceName;

    private Double serviceCharge;

    public static ServicesInfoDTO form(ServicesInfo servicesInfo){
        ServicesInfoDTO servicesInfoDTO = new ServicesInfoDTO();
        servicesInfoDTO.setId(servicesInfo.getId());
        servicesInfoDTO.setServiceName(servicesInfo.getServiceName());
        servicesInfoDTO.setServiceCharge(servicesInfo.getServiceCharge());
        return servicesInfoDTO;
    }
}
