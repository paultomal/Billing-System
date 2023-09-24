package com.paul.billing_system.service;

import com.paul.billing_system.dto.BookingInvestigationDTO;
import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.*;
import com.paul.billing_system.repository.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingInvestigationServices {
    private final BookingInvestigationRepository bookingRepository;
    private final PatientsRepository patientsRepository;
    private final InvestigationRepository investigationRepository;
    private final SpecialistRepository specialistRepository;
    private final OrganizationRepository organizationRepository;

    private final UserDetailsService userDetailsService;

    public BookingInvestigationServices(BookingInvestigationRepository bookingRepository, PatientsRepository patientsRepository, InvestigationRepository investigationRepository, SpecialistRepository specialistRepository, OrganizationRepository organizationRepository, UserDetailsService userDetailsService) {
        this.bookingRepository = bookingRepository;
        this.patientsRepository = patientsRepository;
        this.investigationRepository = investigationRepository;
        this.specialistRepository = specialistRepository;
        this.organizationRepository = organizationRepository;
        this.userDetailsService = userDetailsService;
    }

    public List<Patients> searchPatient(String name) {
        return patientsRepository.findByName(name);
    }

    public List<Investigation> getInvestigations(Long id, Long spId) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Optional<Specialist> specialist = specialistRepository.findById(spId);
        if (organization.isPresent())
            if (specialist.isPresent())
                return investigationRepository.findByOrganizationAndSpecialist(id, spId);
        return null;
    }

    public BookingInvestigation addBooking(BookingInvestigationDTO bookingInvestigationDTO) {

        BookingInvestigation book = new BookingInvestigation();


        List<InvestigationDTO> investigationDTOS = bookingInvestigationDTO.getInvestigations();

        book.setInvestigations(investigationDTOS.stream().map(investigationDTO -> {

            Organization o = organizationRepository.findById(investigationDTO.getOrgId()).orElse(null);
            Specialist s = specialistRepository.findById(investigationDTO.getSpId()).orElse(null);
            Investigation investigation = new Investigation();
            investigation = Investigation.form(investigationDTO);
            investigation.setOrganization(o);
            investigation.setSpecialist(s);

            Optional<Patients> patient = patientsRepository.findById(bookingInvestigationDTO.getPid());
            if (patient.isEmpty()) {
                Patients patients1 = new Patients();
                patients1.setName(bookingInvestigationDTO.getPname());
                patients1.setContact(bookingInvestigationDTO.getContact());
                patients1.setOrganization(o);
                patients1.setSpecialist(s);
                patients1 = patientsRepository.save(patients1);
                book.setPatients(patients1);
            } else {
                book.setPatients(patient.get());
            }
            return investigation;
        }).toList());


        return bookingRepository.save(book);
    }
}
