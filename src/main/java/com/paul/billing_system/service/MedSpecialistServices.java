package com.paul.billing_system.service;

import com.paul.billing_system.dto.CompoundersChamberDTO;
import com.paul.billing_system.dto.MedSpecialistDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.entity.MedSpecialist;
import com.paul.billing_system.repository.CompoundersRepository;
import com.paul.billing_system.repository.MedSpecialistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedSpecialistServices {
    private final MedSpecialistRepository medSpecialistRepository;
    private final CompoundersRepository compoundersRepository;

    public MedSpecialistServices(MedSpecialistRepository medSpecialistRepository, CompoundersRepository compoundersRepository) {
        this.medSpecialistRepository = medSpecialistRepository;
        this.compoundersRepository = compoundersRepository;
    }

    public MedSpecialist saveMedSpecialist(MedSpecialistDTO medSpecialistDTO) {
        MedSpecialist medSpecialist = new MedSpecialist();
        medSpecialist.setMedSpecName(medSpecialistDTO.getMedSpecName());
        medSpecialist.setNoOfDoctors(medSpecialistDTO.getNoOfDoctors());
        medSpecialist.setDoctors(medSpecialistDTO.getDoctors());
        return medSpecialistRepository.save(medSpecialist);
    }

    public List<MedSpecialist> getAllMedSpecialist() {
        return medSpecialistRepository.findAll();
    }

    public MedSpecialist saveCompounder(Long id, CompoundersChamberDTO compoundersChamberDTO){
        Compounders compounders = compoundersRepository.save(Compounders.formCompounderChamber(compoundersChamberDTO));
        Optional<MedSpecialist> medSpecialist = medSpecialistRepository.findById(id);
        medSpecialist.get().setCompounders(compounders);
        return medSpecialistRepository.save(medSpecialist.get());
    }
}
