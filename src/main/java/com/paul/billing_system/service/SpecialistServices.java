package com.paul.billing_system.service;

import com.paul.billing_system.dto.SpecialistDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialistServices {
    private final SpecialistRepository specialistRepository;

    private final OrganizationRepository organizationRepository;

    public SpecialistServices(SpecialistRepository specialistRepository, OrganizationRepository organizationRepository) {
        this.specialistRepository = specialistRepository;
        this.organizationRepository = organizationRepository;
    }

    public Specialist saveSpecialist(Long id, SpecialistDTO specialistDTO) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Specialist specialist = new Specialist();
        if (organization.isPresent()) {
            specialist.setMedSpecName(specialistDTO.getMedSpecName());
            specialistRepository.save(specialist);
            organization.get().getSpecialists().add(specialist);
            organizationRepository.save(organization.get());
        }
        return specialist;
    }

    public List<Specialist> getAllMedSpecialist() {
        return specialistRepository.findAll();
    }

    public Specialist getMedicalSpecialist(Long id) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        if (specialist.isPresent())
            return specialist.get();
        return new Specialist();
    }

/*    public MedSpecialist saveCompounder(Long id, CompoundersChamberDTO compoundersChamberDTO) {
        Compounders compounders = compoundersRepository.save(Compounders.formCompounderChamber(compoundersChamberDTO));
        Optional<MedSpecialist> medSpecialist = medSpecialistRepository.findById(id);
        medSpecialist.get().setCompounders(compounders);
        return medSpecialistRepository.save(medSpecialist.get());
    }*/
}
