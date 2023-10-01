package com.paul.billing_system.service;

import com.paul.billing_system.dto.PatientDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Patient;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.PatientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final OrganizationRepository organizationRepository;


    public PatientService(PatientRepository patientRepository, OrganizationRepository organizationRepository) {
        this.patientRepository = patientRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public Patient savePatients(Long id, PatientDTO patientDTO) {

        Patient patients = new Patient();

        Optional<Organization> organization1 = organizationRepository.findById(id);

        if (organization1.isPresent()) {
            patients.setName(patientDTO.getName());
            patients.setContact(patientDTO.getContact());
            patients.setSince(patientDTO.getSince());

            Organization organization = organizationRepository.findById(patientDTO.getOrgId()).orElseThrow(RuntimeException::new);
            patients.setOrganization(organization);

            patientRepository.save(patients);
        }
        return patients;
    }

    public List<Patient> getAllPatients(Long id, PageRequest pageRequest) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent())
            return patientRepository.findByOrganization(id, pageRequest);
        return null;
    }

    public Patient getPatientById(Long id) {
        Optional<Patient> patients = patientRepository.findById(id);
        return patients.orElseGet(Patient::new);
    }

    public Patient updatePatient(PatientDTO patientDTO, Long id) {
        Optional<Patient> patients = patientRepository.findById(id);

        if (patients.isPresent()) {
            patients.get().setName(patientDTO.getName());
            patients.get().setContact(patientDTO.getContact());
            patients.get().setUpdatedAt(new Date());

            return patientRepository.save(patients.get());
        }

        return null;
    }

    public List<Patient> searchPatient(Long orgId, String name, PageRequest pageRequest) {
        return patientRepository.findByName(orgId, name, pageRequest);
    }
}
