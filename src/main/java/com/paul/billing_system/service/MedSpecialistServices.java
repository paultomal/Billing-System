package com.paul.billing_system.service;

import com.paul.billing_system.dto.CompoundersChamberDTO;
import com.paul.billing_system.dto.MedSpecialistDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.entity.MedSpecialist;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.repository.CompoundersRepository;
import com.paul.billing_system.repository.MedSpecialistRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedSpecialistServices {
    private final MedSpecialistRepository medSpecialistRepository;
    private final CompoundersRepository compoundersRepository;

    private final OrganizationRepository organizationRepository;

    public MedSpecialistServices(MedSpecialistRepository medSpecialistRepository, CompoundersRepository compoundersRepository, OrganizationRepository organizationRepository) {
        this.medSpecialistRepository = medSpecialistRepository;
        this.compoundersRepository = compoundersRepository;
        this.organizationRepository = organizationRepository;
    }

    public MedSpecialist saveMedSpecialist(Long id, MedSpecialistDTO medSpecialistDTO) {
        Optional<Organization> organization = organizationRepository.findById(id);
        MedSpecialist medSpecialist = new MedSpecialist();
        if (organization.isPresent()) {
            if (organization.get().getType().equals(OrganizationTypes.CHAMBER)) {
                medSpecialist.setMedSpecName(medSpecialistDTO.getMedSpecName());
                medSpecialist.setNoOfDoctors(medSpecialistDTO.getNoOfDoctors());
                //medSpecialist.setDoctors(medSpecialistDTO.getDoctors());
                medSpecialistRepository.save(medSpecialist);
                organization.get().getMedSpecialists().add(medSpecialist);
                organizationRepository.save(organization.get());
            }
        }
        return medSpecialist;
    }

    public List<MedSpecialist> getAllMedSpecialist() {
        return medSpecialistRepository.findAll();
    }

/*    public MedSpecialist saveCompounder(Long id, CompoundersChamberDTO compoundersChamberDTO) {
        Compounders compounders = compoundersRepository.save(Compounders.formCompounderChamber(compoundersChamberDTO));
        Optional<MedSpecialist> medSpecialist = medSpecialistRepository.findById(id);
        medSpecialist.get().setCompounders(compounders);
        return medSpecialistRepository.save(medSpecialist.get());
    }*/
}
