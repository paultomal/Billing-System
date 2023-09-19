package com.paul.billing_system.service;

import com.paul.billing_system.dto.PatientsDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.PatientsRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientsServices {
    private final PatientsRepository patientsRepository;
    private final SpecialistRepository specialistRepository;

    public PatientsServices(PatientsRepository patientsRepository, SpecialistRepository specialistRepository) {
        this.patientsRepository = patientsRepository;
        this.specialistRepository = specialistRepository;
    }

    public Patients savePatients(Long id, PatientsDTO patientsDTO) {

        Patients patients = new Patients();

        Optional<Specialist> specialist = specialistRepository.findById(id);

        if (specialist.isPresent()) {
            patients.setName(patientsDTO.getName());
            patients.setAge(patientsDTO.getAge());
            patients.setSince(patientsDTO.getSince());
            patientsRepository.save(patients);
            specialist.get().getPatients().add(patients);
            specialistRepository.save(specialist.get());
        }
        return patients;
    }

    public List<Patients> getAllPatients(Long id) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        if (specialist.isPresent()) {
            return patientsRepository.findAll();
        }
        return (List<Patients>) new Patients();
    }

    public Patients getPatientById(Long id) {
        Optional<Patients> patients = patientsRepository.findById(id);
        if (patients.isPresent()) {
            return patients.get();
        }
        return new Patients();
    }

    public Patients updatePatient(PatientsDTO patientsDTO, Long id) {
        Optional<Patients> patients = patientsRepository.findById(id);
        if (patients.isPresent()) {
            Patients patients1 = new Patients();
            patients1.setName(patientsDTO.getName());
            patients1.setAge(patientsDTO.getAge());
            return patientsRepository.save(patients1);
        }
        return new Patients();
    }
}
