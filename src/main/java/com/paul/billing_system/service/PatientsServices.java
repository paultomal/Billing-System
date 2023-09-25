package com.paul.billing_system.service;

import com.paul.billing_system.dto.PatientsDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.PatientsRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientsServices {
    private final PatientsRepository patientsRepository;
    private final OrganizationRepository organizationRepository;

    private final SpecialistRepository specialistRepository;

    public PatientsServices(PatientsRepository patientsRepository, OrganizationRepository organizationRepository, SpecialistRepository specialistRepository) {
        this.patientsRepository = patientsRepository;
        this.organizationRepository = organizationRepository;
        this.specialistRepository = specialistRepository;
    }

    public Patients savePatients(Long id, PatientsDTO patientsDTO) {

        Patients patients = new Patients();

        Optional<Organization> organization1 = organizationRepository.findById(id);

        if (organization1.isPresent()) {
            patients.setName(patientsDTO.getName());
            patients.setAge(patientsDTO.getAge());
            patients.setSince(patientsDTO.getSince());

            Organization organization = organizationRepository.findById(patientsDTO.getOrgId()).orElseThrow(RuntimeException::new);
            patients.setOrganization(organization);

            Specialist specialist1 = specialistRepository.findById(patientsDTO.getSpId()).orElseThrow(RuntimeException::new);
            patients.setSpecialist(specialist1);

            patientsRepository.save(patients);
        }
        return patients;
    }

    public List<Patients> getAllPatients(Long id, Long spId, PageRequest pageRequest) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Optional<Specialist> specialist = specialistRepository.findById(spId);
        if (organization.isPresent())
            if (specialist.isPresent()) {
                return patientsRepository.findByOrganizationAndSpecialist(id,spId,pageRequest);
            }
        return null;
    }

    public Patients getPatientById(Long id) {
        Optional<Patients> patients = patientsRepository.findById(id);
        return patients.orElseGet(Patients::new);
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

    public List<Patients> searchPatient(String name, PageRequest pageRequest) {
        return patientsRepository.findByName(name, pageRequest);
    }
}
