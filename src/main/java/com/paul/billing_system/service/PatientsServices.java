package com.paul.billing_system.service;

import com.paul.billing_system.dto.PatientsDTO;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.repository.PatientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientsServices {
    private final PatientsRepository patientsRepository;
    public PatientsServices(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public Patients savePatients(PatientsDTO patientsDTO) {
        Patients patients = new Patients();
        patients.setName(patientsDTO.getName());
        patients.setAge(patientsDTO.getAge());
        return patientsRepository.save(patients);
    }

    public List<Patients> getAllPatients() {
    return patientsRepository.findAll();
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
    if (patients.isPresent()){
        Patients patients1 = new Patients();
        patients1.setName(patientsDTO.getName());
        patients1.setAge(patientsDTO.getAge());
        return patientsRepository.save(patients1);
    }
    return new Patients();
    }
}
