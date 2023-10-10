package com.paul.billing_system.service;

import com.paul.billing_system.dto.AppointmentBookingDTO;
import com.paul.billing_system.dto.RevenueDTO;
import com.paul.billing_system.entity.AppointmentBooking;
import com.paul.billing_system.entity.Doctor;
import com.paul.billing_system.entity.Patient;
import com.paul.billing_system.repository.AppointmentBookingRepository;
import com.paul.billing_system.repository.DoctorRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.PatientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

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

        Optional<Patient> patient = Optional.empty();
        List<AppointmentBooking> previousAppointments = null;
        if (appointmentBookingDTO.getPatientId() != null)
            patient = patientRepository.findById(appointmentBookingDTO.getPatientId());

        if (patient.isPresent()) {
            appointmentBooking.setPatient(patient.get());
            previousAppointments = appointmentBookingRepository.findAllByDoctorIdAndPatientId(appointmentBookingDTO.getDoc_id(), patient.get().getId());
        } else {
            Patient newPatient = new Patient();
            newPatient.setName(appointmentBookingDTO.getPatientName());
            newPatient.setContact(appointmentBookingDTO.getPatientContact());
            newPatient.setOrganization(organizationRepository.findById(appointmentBookingDTO.getOrgId()).orElseThrow());

            appointmentBooking.setPatient(patientRepository.save(newPatient));
        }

        Doctor doctor = doctorRepository.findById(appointmentBookingDTO.getDoc_id()).orElseThrow();
        appointmentBooking.setDoctor(doctor);
        appointmentBooking.setSlot(appointmentBookingDTO.getSlot());

        if(previousAppointments == null || previousAppointments.isEmpty()) {
            appointmentBooking.setConsultationFee(doctor.getConsultationFee());
            Double discount = Double.parseDouble(appointmentBooking.getConsultationFee()) * (Double.parseDouble(appointmentBookingDTO.getDiscount()) / 100);
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            appointmentBooking.setDiscount(decimalFormat.format(discount));
            appointmentBooking.setTotalFees(Double.parseDouble(doctor.getConsultationFee()) - Double.parseDouble(doctor.getConsultationFee()) * (Double.parseDouble(appointmentBookingDTO.getDiscount()) / 100));
        }
        else {
            appointmentBooking.setConsultationFee(doctor.getFollowUp());
            Double discount = Double.parseDouble(appointmentBooking.getConsultationFee()) * (Double.parseDouble(appointmentBookingDTO.getDiscount()) / 100);
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            appointmentBooking.setDiscount(decimalFormat.format(discount));
            appointmentBooking.setTotalFees(Double.parseDouble(doctor.getFollowUp()) - Double.parseDouble(doctor.getFollowUp()) * (Double.parseDouble(appointmentBookingDTO.getDiscount()) / 100));
        }

        return AppointmentBookingDTO.form(appointmentBookingRepository.save(appointmentBooking));
    }

    public AppointmentBookingDTO getAppointment(Long id) {
        return AppointmentBookingDTO.form(appointmentBookingRepository.findById(id).orElse(null));
    }

    public List<AppointmentBookingDTO> getAppointmentsByOrg(Long orgId, PageRequest pageRequest) {
        return appointmentBookingRepository.findAllByOrganizationId(orgId, pageRequest)
                .stream()
                .map(AppointmentBookingDTO::form)
                .toList();
    }

    public RevenueDTO getTotalRevenueForHospital(Long orgId) {
        List<AppointmentBooking> appointments = appointmentBookingRepository.findAllByOrganizationId(orgId);

        RevenueDTO revenueDTO = new RevenueDTO();
        revenueDTO.setTotalRevenue(appointments
                .stream()
                .map(AppointmentBooking::getTotalFees)
                .reduce(0.0, Double::sum));
        revenueDTO.setTotalDiscount(appointments
                .stream()
                .map(a -> Double.parseDouble(a.getDiscount()))
                .reduce(0.0, (Double::sum)));

        return revenueDTO;
    }

    public List<AppointmentBookingDTO> searchAppointment(Long orgId, String patientName, PageRequest pageRequest) {
        return appointmentBookingRepository.findByOrganizationAndPatient(orgId, patientName, pageRequest)
                .stream()
                .map(AppointmentBookingDTO::form)
                .toList();
    }

    public Long appointmentCount(Long orgId) {
        return (long) appointmentBookingRepository.findAllByOrganizationId(orgId).size();
    }
}
