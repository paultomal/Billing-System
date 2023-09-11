package com.paul.billing_system.service;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.entity.MedSpecialist;
import com.paul.billing_system.repository.DoctorRepository;
import com.paul.billing_system.repository.MedSpecialistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServices {
    private final DoctorRepository doctorRepository;
    private final MedSpecialistRepository medSpecialistRepository;

    public DoctorServices(DoctorRepository doctorRepository, MedSpecialistRepository medSpecialistRepository) {
        this.doctorRepository = doctorRepository;
        this.medSpecialistRepository = medSpecialistRepository;
    }

    public Doctors save(Long id, DoctorDTO doctorDTO) {
        Optional<MedSpecialist> medSpecialist = medSpecialistRepository.findById(id);
        Doctors doctors = new Doctors();
        if (medSpecialist.isPresent()) {
            doctors.setDoctorName(doctorDTO.getDoctorName());
            doctors.setDoctorDegree(doctorDTO.getDoctorDegree());
            doctorRepository.save(doctors);
            medSpecialist.get().getDoctors().add(doctors);
            medSpecialistRepository.save(medSpecialist.get());
        }
        return doctors;
    }

    public Doctors getDoctorById(Long id) {
        Optional<Doctors> doctors = doctorRepository.findById(id);
        if (doctors.isPresent()) {
            return doctors.get();
        }
        return new Doctors();
    }

    public List<Doctors> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctors updateDoctor(Long id, DoctorDTO doctorDTO) {
        Optional<Doctors> doctors = doctorRepository.findById(id);
        if (doctors.isPresent()) {
            Doctors doctors1 = doctors.get();
            doctors1.setDoctorName(doctorDTO.getDoctorName());
            doctors1.setDoctorDegree(doctorDTO.getDoctorDegree());
            return doctorRepository.save(doctors1);
        }
        return new Doctors();
    }
}