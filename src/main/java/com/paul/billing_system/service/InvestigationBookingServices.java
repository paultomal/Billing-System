package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationBookingDTO;
import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.InvestigationBooking;
import com.paul.billing_system.entity.Patient;
import com.paul.billing_system.repository.InvestigationBookingRepository;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.OrganizationRepository;
import com.paul.billing_system.repository.PatientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationBookingServices {
    private final InvestigationBookingRepository bookingRepository;
    private final PatientRepository patientRepository;
    private final InvestigationRepository investigationRepository;
    private final OrganizationRepository organizationRepository;

    public InvestigationBookingServices(InvestigationBookingRepository investigationBookingRepository, PatientRepository patientRepository, InvestigationRepository investigationRepository, OrganizationRepository organizationRepository) {
        this.bookingRepository = investigationBookingRepository;
        this.patientRepository = patientRepository;
        this.investigationRepository = investigationRepository;
        this.organizationRepository = organizationRepository;
    }

    public List<Investigation> getInvestigations(PageRequest pageRequest) {
        return investigationRepository.findAll(pageRequest).getContent();
    }

    public InvestigationBooking addBooking(InvestigationBookingDTO investigationBookingDTO) {

        InvestigationBooking book = new InvestigationBooking();

        Optional<Patient> patient = patientRepository.findById(investigationBookingDTO.getPid());
        if (patient.isEmpty()) {
            Patient patients1 = new Patient();
            patients1.setName(investigationBookingDTO.getP_name());
            patients1.setContact(investigationBookingDTO.getContact());
            patients1 = patientRepository.save(patients1);
            book.setPatient(patients1);
        } else {
            book.setPatient(patient.get());
        }

        book.setOrganization(organizationRepository.findById(investigationBookingDTO.getOrg_id()).orElse(null));

        List<InvestigationDTO> investigationDTOS = investigationBookingDTO.getInvestigations();
        book.setInvestigations(investigationDTOS.stream().map(Investigation::form).toList());

        book.setTotal(investigationBookingDTO.getTotal());

        return bookingRepository.save(book);
    }

    public InvestigationBooking getInvestigationBookingById(Long bookInvestigationId) {
        Optional<InvestigationBooking> investigationBooking = bookingRepository.findById(bookInvestigationId);
        return investigationBooking.orElse(null);
    }

    public List<InvestigationBooking> getInvestigationBookingsByOrgId(Long orId, PageRequest pageRequest) {
        return bookingRepository.findByOrganization(orId, pageRequest);
    }
}
