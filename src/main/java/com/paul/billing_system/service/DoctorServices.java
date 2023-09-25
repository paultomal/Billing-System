package com.paul.billing_system.service;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.enums.DaysOfWeek;
import com.paul.billing_system.repository.DoctorRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServices {
    private final DoctorRepository doctorRepository;
    private final SpecialistRepository specialistRepository;
    private final OrganizationRepository organizationRepository;

    public DoctorServices(DoctorRepository doctorRepository, SpecialistRepository specialistRepository, OrganizationRepository organizationRepository) {
        this.doctorRepository = doctorRepository;
        this.specialistRepository = specialistRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public Doctors save(Long id, DoctorDTO doctorDTO) {
        Optional<Organization> organization1 = organizationRepository.findById(id);
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

            Organization organization = organizationRepository.findById(doctorDTO.getOrgId()).orElseThrow(RuntimeException::new);

            Specialist specialist1 = specialistRepository.findById(doctorDTO.getSpId()).orElseThrow(RuntimeException::new);

            doctors.setOrganization(organization);
            doctors.setSpecialist(specialist1);

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
        Optional<Specialist> specialist = specialistRepository.findById(sId);
        if (organization.isPresent())
            if (specialist.isPresent())
                return doctorRepository.findByOrganizationAndSpecialist(id,sId,pageRequest);
        return null;
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
            doctor.get().setLastUpdateTime(new Date());

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
