package com.paul.billing_system.service;

import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.repository.BookingInvestigationRepository;
import com.paul.billing_system.repository.InvestigationRepository;
import com.paul.billing_system.repository.PatientsRepository;
import com.paul.billing_system.repository.SpecialistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingInvestigationServices {
    private final BookingInvestigationRepository bookingRepository;
    private final PatientsRepository patientsRepository;
    private final InvestigationRepository investigationRepository;
    private final SpecialistRepository specialistRepository;

    public BookingInvestigationServices(BookingInvestigationRepository bookingRepository, PatientsRepository patientsRepository, InvestigationRepository investigationRepository, SpecialistRepository specialistRepository) {
        this.bookingRepository = bookingRepository;
        this.patientsRepository = patientsRepository;
        this.investigationRepository = investigationRepository;
        this.specialistRepository = specialistRepository;
    }

    public List<Patients> searchPatient(String name) {
        return patientsRepository.findByName(name);
    }

    public Page<Investigation> getInvestigations(Long id, int offset, int pageSize) {
        Optional<Specialist> specialist = specialistRepository.findById(id);
        if (specialist.isPresent())
            return investigationRepository.findAll(PageRequest.of(offset, pageSize));
        return null;
    }
}
