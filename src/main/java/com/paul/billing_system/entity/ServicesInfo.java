package com.paul.billing_system.entity;

import com.paul.billing_system.dto.ServicesInfoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServicesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    private Double serviceCharge;
    public static ServicesInfo form(ServicesInfoDTO servicesInfoDTO){
        ServicesInfo servicesInfo = new ServicesInfo();
        servicesInfo.setServiceName(servicesInfoDTO.getServiceName());
        servicesInfo.setServiceCharge(servicesInfoDTO.getServiceCharge());
        return servicesInfo;
    }
}
