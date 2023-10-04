package com.paul.billing_system.service;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.dto.DoctorSlotDTO;
import com.paul.billing_system.entity.Doctor;
import com.paul.billing_system.entity.DoctorSlot;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Speciality;
import com.paul.billing_system.enums.DaysOfWeek;
import com.paul.billing_system.repository.DoctorRepository;
import com.paul.billing_system.repository.DoctorSlotRepository;
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
    private final DoctorSlotRepository doctorSlotRepository;

    public DoctorService(DoctorRepository doctorRepository, SpecialityRepository specialityRepository, OrganizationRepository organizationRepository, DoctorSlotRepository doctorSlotRepository) {
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
        this.organizationRepository = organizationRepository;
        this.doctorSlotRepository = doctorSlotRepository;
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

            List<DoctorSlot> doctorSlotList = doctorDTO.getDoctorSlotDTOList().stream().map(this::form).toList();
            doctorSlotList = doctorSlotRepository.saveAll(doctorSlotList);
            doctor.setDoctorSlots(doctorSlotList);

            List<Organization> organizationList = doctorDTO.getOrgId().stream().map(o-> organizationRepository.findById(o).orElseThrow()).toList();

            List<Speciality> specialityList = doctorDTO.getSpId().stream().map(s -> specialityRepository.findById(s).orElseThrow()).toList();

            doctor.setOrganizationList(organizationList);
            doctor.setSpecialityList(specialityList);

            doctorRepository.save(doctor);
        }
        return doctor;
    }
    public DoctorSlot form(DoctorSlotDTO doctorSlotDTO) {
        DoctorSlot doctorSlot = new DoctorSlot();
        doctorSlot.setDay(DaysOfWeek.getDaysByLabel(doctorSlotDTO.getDay()));
        doctorSlot.setTime(doctorSlotDTO.getTime());
        return doctorSlot;
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

            return doctorRepository.save(doctor.get());
        }
        return null;
    }

    public List<Doctor> searchDoctor(String name, PageRequest pageRequest) {
       return doctorRepository.findByName(name, pageRequest);
    }

    public List<Doctor> searchDoctorUnderOrg(Long orgId, String name, PageRequest pageRequest) {
        return doctorRepository.findDoctorByNameUnderOrg(organizationRepository.findById(orgId).orElseThrow(), name, pageRequest);
    }
}
