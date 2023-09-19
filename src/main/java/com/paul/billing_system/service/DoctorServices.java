package com.paul.billing_system.service;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.enums.DaysOfWeek;
import com.paul.billing_system.repository.DoctorRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServices {
    private final DoctorRepository doctorRepository;
    private final SpecialistRepository specialistRepository;

    public DoctorServices(DoctorRepository doctorRepository, SpecialistRepository specialistRepository) {
        this.doctorRepository = doctorRepository;
        this.specialistRepository = specialistRepository;
    }

    public Doctors save(Long id, DoctorDTO doctorDTO) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        Doctors doctors = new Doctors();
        if (specialist.isPresent()) {
            doctors.setName(doctorDTO.getName());
            doctors.setContact(doctorDTO.getContact());
            doctors.setDegrees(doctorDTO.getDegrees());
            doctors.setEmail(doctorDTO.getEmail());
            doctors.setFollowUp(doctorDTO.getFollowUp());
            doctors.setConsultation(doctorDTO.getConsultation());
            doctors.setMinDiscount(doctorDTO.getMinDiscount());
            doctors.setMaxDiscount(doctorDTO.getMaxDiscount());

            DaysOfWeek days = DaysOfWeek.getDaysByLabel(doctorDTO.getDay());
            doctors.setDay(days);

            doctors.setTime(doctorDTO.getTime());
            doctorRepository.save(doctors);
            specialist.get().getDoctors().add(doctors);
            specialist.get().setNoOfDoctor(specialist.get().getNoOfDoctor() + 1);
            specialistRepository.save(specialist.get());
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

    public Page<Doctors> getAllDoctors(Long id, int offset, int pageSize) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        if (specialist.isPresent())
            return doctorRepository.findAll(PageRequest.of(offset, pageSize));
        return null;
    }

    public Doctors updateDoctor(Long id, DoctorDTO doctorDTO) {
        Optional<Doctors> doctors = doctorRepository.findById(id);
        if (doctors.isPresent()) {
            Doctors doctors1 = doctors.get();
            doctors1.setName(doctorDTO.getName());
            doctors1.setContact(doctorDTO.getContact());
            doctors1.setDegrees(doctorDTO.getDegrees());
            doctors1.setEmail(doctorDTO.getEmail());
            doctors1.setFollowUp(doctorDTO.getFollowUp());
            doctors1.setConsultation(doctorDTO.getConsultation());
            doctors1.setMinDiscount(doctorDTO.getMinDiscount());
            doctors1.setMaxDiscount(doctorDTO.getMaxDiscount());

            DaysOfWeek days = DaysOfWeek.getDaysByLabel(doctorDTO.getDay());
            doctors1.setDay(days);

            doctors1.setTime(doctorDTO.getTime());
            return doctorRepository.save(doctors1);
        }
        return new Doctors();
    }
}
