package com.paul.billing_system.entity;

import com.paul.billing_system.dto.PatientsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    private int age;

    private LocalDate since;

    public static Patients form(PatientsDTO patientsDTO) {
    Patients patients = new Patients();
    patients.setName(patientsDTO.getName());
    patients.setAge(patientsDTO.getAge());;
    patients.setSince(patientsDTO.getSince());
    return patients;
    }
}
