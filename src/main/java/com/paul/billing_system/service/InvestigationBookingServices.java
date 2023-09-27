package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationBookingDTO;
import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.*;
import com.paul.billing_system.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationBookingServices {
    private final InvestigationBookingRepository bookingRepository;
    private final PatientsRepository patientsRepository;
    private final InvestigationRepository investigationRepository;
    private final SpecialityRepository specialityRepository;
    private final OrganizationRepository organizationRepository;

    public InvestigationBookingServices(InvestigationBookingRepository investigationBookingRepository, PatientsRepository patientsRepository, InvestigationRepository investigationRepository, SpecialityRepository specialityRepository, OrganizationRepository organizationRepository) {
        this.bookingRepository = investigationBookingRepository;
        this.patientsRepository = patientsRepository;
        this.investigationRepository = investigationRepository;
        this.specialityRepository = specialityRepository;
        this.organizationRepository = organizationRepository;
    }

    public List<Patients> searchPatient(String name, PageRequest pageRequest) {
        return patientsRepository.findByName(name, pageRequest);
    }

    public List<Investigation> getInvestigations( PageRequest pageRequest) {
            return investigationRepository.findAll(pageRequest).getContent();
    }

    public InvestigationBooking addBooking(InvestigationBookingDTO investigationBookingDTO) {

        InvestigationBooking book = new InvestigationBooking();

        Optional<Patients> patient = patientsRepository.findById(investigationBookingDTO.getPid());
        if (patient.isEmpty()) {
            Patients patients1 = new Patients();
            patients1.setName(investigationBookingDTO.getP_name());
            patients1.setContact(investigationBookingDTO.getContact());
            patients1 = patientsRepository.save(patients1);
            book.setPatients(patients1);
        } else {
            book.setPatients(patient.get());
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
