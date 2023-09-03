package com.paul.billing_system.dto;

import com.paul.billing_system.entity.ServicesInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesInfoDTO {
    private String serviceName;
    private Double serviceCharge;

    public static ServicesInfoDTO form(ServicesInfo servicesInfo){
        ServicesInfoDTO servicesInfoDTO = new ServicesInfoDTO();
        servicesInfoDTO.setServiceName(servicesInfoDTO.getServiceName());
        servicesInfoDTO.setServiceCharge(servicesInfoDTO.getServiceCharge());
        return servicesInfoDTO;
    }
}
