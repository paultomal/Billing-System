package com.paul.billing_system.service;

import com.paul.billing_system.dto.AppointmentBookingDTO;
import com.paul.billing_system.entity.AppointmentBooking;
import com.paul.billing_system.entity.Patient;
import com.paul.billing_system.repository.AppointmentBookingRepository;
import com.paul.billing_system.repository.DoctorRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentBookingService {
    private final AppointmentBookingRepository appointmentBookingRepository;
    private final OrganizationRepository organizationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentBookingService(AppointmentBookingRepository appointmentBookingRepository, OrganizationRepository organizationRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentBookingRepository = appointmentBookingRepository;
        this.organizationRepository = organizationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public AppointmentBookingDTO makeAppointment(AppointmentBookingDTO appointmentBookingDTO) {
        AppointmentBooking appointmentBooking = new AppointmentBooking();
        appointmentBooking.setOrganization(organizationRepository.findById(appointmentBookingDTO.getOrgId()).orElseThrow());

        if(patientRepository.findById(appointmentBookingDTO.getP_id()).isPresent()) {
            appointmentBooking.setPatient(patientRepository.findById(appointmentBookingDTO.getP_id()).get());
        }
        else {
            Patient patient = new Patient();
            patient.setName(appointmentBookingDTO.getP_name());
            patient.setContact(appointmentBookingDTO.getContact());
            patient.setOrganization(organizationRepository.findById(appointmentBookingDTO.getOrgId()).orElseThrow());

            appointmentBooking.setPatient(patientRepository.save(patient));
        }
        
        appointmentBooking.setDoctor(doctorRepository.findById(appointmentBookingDTO.getDoc_id()).orElseThrow());
        appointmentBooking.setConsultationFee(appointmentBookingDTO.getConsultationFee());
        appointmentBooking.setDiscount(appointmentBookingDTO.getDiscount());
        appointmentBooking.setTotalFees(appointmentBookingDTO.getTotalFees());

        return AppointmentBookingDTO.form(appointmentBookingRepository.save(appointmentBooking));
    }

    public AppointmentBookingDTO getAppointment(Long id) {
        return AppointmentBookingDTO.form(appointmentBookingRepository.findById(id).orElse(null));
    }

    public List<AppointmentBookingDTO> getAppointmentsByOrg(Long orgId, PageRequest pageRequest) {
        return appointmentBookingRepository.findAllByOrganizationId(orgId, pageRequest).stream().map(AppointmentBookingDTO::form).toList();
    }
}
