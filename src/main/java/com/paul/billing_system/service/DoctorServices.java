package com.paul.billing_system.service;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.entity.Doctors;
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
public class DoctorServices {
    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final OrganizationRepository organizationRepository;

    public DoctorServices(DoctorRepository doctorRepository, SpecialityRepository specialityRepository, OrganizationRepository organizationRepository) {
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public Doctors save(Long orgId, DoctorDTO doctorDTO) {
        Optional<Organization> organization1 = organizationRepository.findById(orgId);
        Doctors doctors = new Doctors();
        if (organization1.isPresent()) {

            doctors.setName(doctorDTO.getName());

            doctors.setContact(doctorDTO.getContact());
            doctors.setDegrees(doctorDTO.getDegrees());
            doctors.setEmail(doctorDTO.getEmail());
            doctors.setFollowUp(doctorDTO.getFollowUp());
            doctors.setConsultationFee(doctorDTO.getConsultation());
            doctors.setMinDiscount(doctorDTO.getMinDiscount());
            doctors.setMaxDiscount(doctorDTO.getMaxDiscount());

            DaysOfWeek days = DaysOfWeek.getDaysByLabel(doctorDTO.getDay());
            doctors.setDay(days);

            doctors.setTime(doctorDTO.getTime());

            List<Organization> organizationList = doctorDTO.getOrgId().stream().map(o-> organizationRepository.findById(o).orElseThrow()).toList();

            List<Speciality> specialityList = doctorDTO.getSpId().stream().map(s -> specialityRepository.findById(s).orElseThrow()).toList();

            doctors.setOrganization(organizationList);
            doctors.setSpeciality(specialityList);

            doctorRepository.save(doctors);
        }
        return doctors;
    }

    public Doctors getDoctorById(Long id) {
        Optional<Doctors> doctors = doctorRepository.findById(id);
        return doctors.orElseGet(Doctors::new);
    }

    public List<Doctors> getAllDoctors(Long id, Long sId, PageRequest pageRequest) {
        Optional<Organization> organization = organizationRepository.findById(id);

        return organization.map(value -> doctorRepository.findAllByOrganization(value, pageRequest)).orElse(null);
    }

    public Doctors updateDoctor(Long id, DoctorDTO doctorDTO) {
        Optional<Doctors> doctor = doctorRepository.findById(id);
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

    public List<Doctors> searchDoctor(String name, PageRequest pageRequest) {
       return doctorRepository.findByName(name, pageRequest);
    }
}
