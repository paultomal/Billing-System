package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Hospital;
import com.paul.billing_system.enums.OrganizationTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    private String name;

    public static HospitalDTO hospitalForm(Hospital hospital){
       HospitalDTO hospitalDTO = new HospitalDTO();
       hospitalDTO.setName(hospital.getName());
       return hospitalDTO;
    }
}
