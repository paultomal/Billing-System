package com.paul.billing_system.service;

import com.paul.billing_system.dto.SpecialistDTO;
import com.paul.billing_system.entity.Department;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.DepartmentRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialistServices {
    private final SpecialistRepository specialistRepository;

    private final DepartmentRepository departmentRepository;

    public SpecialistServices(SpecialistRepository specialistRepository, DepartmentRepository departmentRepository) {
        this.specialistRepository = specialistRepository;
        this.departmentRepository = departmentRepository;
    }

    public Specialist saveSpecialist(Long id, SpecialistDTO specialistDTO) {
        Optional<Department> department = departmentRepository.findById(id);
        Specialist specialist = new Specialist();
        if (department.isPresent()) {
            specialist.setSpecialityName(specialistDTO.getSpecialityName());
            specialistRepository.save(specialist);
            department.get().getSpecialists().add(specialist);
            departmentRepository.save(department.get());
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
