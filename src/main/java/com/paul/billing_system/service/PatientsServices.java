package com.paul.billing_system.service;

import com.paul.billing_system.dto.PatientsDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.PatientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientsServices {
    private final PatientsRepository patientsRepository;
    private final OrganizationRepository organizationRepository;

    public PatientsServices(PatientsRepository patientsRepository, OrganizationRepository organizationRepository) {
        this.patientsRepository = patientsRepository;
        this.organizationRepository = organizationRepository;
    }

    public Patients savePatients(Long id, PatientsDTO patientsDTO) {

        Patients patients = new Patients();

        Optional<Organization> organization = organizationRepository.findById(id);

        if (organization.isPresent()) {
            if (organization.get().getType().equals(OrganizationTypes.HOSPITAL)) {
                patients.setName(patientsDTO.getName());
                patients.setAge(patientsDTO.getAge());
                patientsRepository.save(patients);
                organization.get().getPatients().add(patients);
                organizationRepository.save(organization.get());
            }
        }
        return patients;
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
        if (patients.isPresent()) {
            Patients patients1 = new Patients();
            patients1.setName(patientsDTO.getName());
            patients1.setAge(patientsDTO.getAge());
            return patientsRepository.save(patients1);
        }
        return new Patients();
    }
}
