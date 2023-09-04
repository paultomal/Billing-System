package com.paul.billing_system.entity;

import com.paul.billing_system.dto.PatientsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    public static Patients form(PatientsDTO patientsDTO) {
    Patients patients = new Patients();
    patients.setName(patientsDTO.getName());
    patients.setAge(patientsDTO.getAge());;
    return patients;
    }
}
