package com.paul.billing_system.service;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.entity.Doctor;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Speciality;
import com.paul.billing_system.enums.DaysOfWeek;
import com.paul.billing_system.repository.DoctorRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.SpecialityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final OrganizationRepository organizationRepository;

    public DoctorService(DoctorRepository doctorRepository, SpecialityRepository specialityRepository, OrganizationRepository organizationRepository) {
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public Doctor save(Long id, DoctorDTO doctorDTO) {
        Optional<Organization> organization1 = organizationRepository.findById(id);
        Doctor doctor = new Doctor();
        if (organization1.isPresent()) {

            doctor.setName(doctorDTO.getName());

            doctor.setContact(doctorDTO.getContact());
            doctor.setDegrees(doctorDTO.getDegrees());
            doctor.setEmail(doctorDTO.getEmail());
            doctor.setFollowUp(doctorDTO.getFollowUp());
            doctor.setConsultationFee(doctorDTO.getConsultation());
            doctor.setMinDiscount(doctorDTO.getMinDiscount());
            doctor.setMaxDiscount(doctorDTO.getMaxDiscount());

            DaysOfWeek days = DaysOfWeek.getDaysByLabel(doctorDTO.getDay());
            doctor.setDay(days);

            doctor.setTime(doctorDTO.getTime());

            List<Organization> organizationList = doctorDTO.getOrgId().stream().map(o-> organizationRepository.findById(o).orElseThrow()).toList();

            List<Speciality> specialityList = doctorDTO.getSpId().stream().map(s -> specialityRepository.findById(s).orElseThrow()).toList();

            doctor.setOrganizationList(organizationList);
            doctor.setSpecialityList(specialityList);

            doctorRepository.save(doctor);
        }
        return doctor;
    }

    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctors = doctorRepository.findById(id);
        return doctors.orElseGet(Doctor::new);
    }

    public List<Doctor> getAllDoctors(Long id, Long sId, PageRequest pageRequest) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Optional<Speciality> speciality = specialityRepository.findById(sId);

        return doctorRepository.findAllByOrganizationListContainsAndSpecialityListContains(organization.orElse(null), speciality.orElse(null), pageRequest);
    }

    public Doctor updateDoctor(Long id, DoctorDTO doctorDTO) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            doctor.get().setName(doctorDTO.getName());
            doctor.get().setContact(doctorDTO.getContact());
            doctor.get().setDegrees(doctorDTO.getDegrees());
            doctor.get().setEmail(doctorDTO.getEmail());
            doctor.get().setFollowUp(doctorDTO.getFollowUp());
            doctor.get().setConsultationFee(doctorDTO.getConsultation());
            doctor.get().setMinDiscount(doctorDTO.getMinDiscount());
            doctor.get().setMaxDiscount(doctorDTO.getMaxDiscount());
            doctor.get().setUpdatedAt(new Date());

            DaysOfWeek days = DaysOfWeek.getDaysByLabel(doctorDTO.getDay());
            doctor.get().setDay(days);

            doctor.get().setTime(doctorDTO.getTime());

            return doctorRepository.save(doctor.get());
        }
        return null;
    }

    public List<Doctor> searchDoctor(String name, PageRequest pageRequest) {
       return doctorRepository.findByName(name, pageRequest);
    }
}
