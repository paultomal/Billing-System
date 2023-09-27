package com.paul.billing_system.service;

import com.paul.billing_system.dto.PatientsDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.PatientsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Transactional
    public Patients savePatients(Long id, PatientsDTO patientsDTO) {

        Patients patients = new Patients();

        Optional<Organization> organization1 = organizationRepository.findById(id);

        if (organization1.isPresent()) {
            patients.setName(patientsDTO.getName());
            patients.setContact(patientsDTO.getContact());
            patients.setSince(patientsDTO.getSince());

            Organization organization = organizationRepository.findById(patientsDTO.getOrgId()).orElseThrow(RuntimeException::new);
            patients.setOrganization(organization);

            patientsRepository.save(patients);
        }
        return patients;
    }

    public List<Patients> getAllPatients(Long id, PageRequest pageRequest) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if (organization.isPresent())
            return patientsRepository.findByOrganization(id, pageRequest);
        return null;
    }

    public Patients getPatientById(Long id) {
        Optional<Patients> patients = patientsRepository.findById(id);
        return patients.orElseGet(Patients::new);
    }

    public Patients updatePatient(PatientsDTO patientsDTO, Long id) {
        Optional<Patients> patients = patientsRepository.findById(id);

        if (patients.isPresent()) {
            patients.get().setName(patientsDTO.getName());
            patients.get().setContact(patientsDTO.getContact());
            patients.get().setUpdatedAt(new Date());

            return patientsRepository.save(patients.get());
        }

        return null;
    }

    public List<Patients> searchPatient(String name, PageRequest pageRequest) {
        return patientsRepository.findByName(name, pageRequest);
    }
}
