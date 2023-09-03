package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Patients;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientsDTO {
    private String name;
    private int age;

    public static PatientsDTO form(Patients patients){
        PatientsDTO patientsDTO = new PatientsDTO();
        patientsDTO.setName(patients.getName());
        patientsDTO.setAge(patients.getAge());
        return patientsDTO;
    }
}
