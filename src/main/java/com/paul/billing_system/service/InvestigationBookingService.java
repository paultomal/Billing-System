package com.paul.billing_system.service;

import com.paul.billing_system.dto.InvestigationBookingDTO;
import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.InvestigationBooking;
import com.paul.billing_system.entity.OrgInvestigationPrice;
import com.paul.billing_system.entity.Patient;
import com.paul.billing_system.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvestigationBookingService {
    private final InvestigationBookingRepository bookingRepository;
    private final PatientRepository patientRepository;
    private final InvestigationRepository investigationRepository;
    private final OrganizationRepository organizationRepository;

    private final OrgInvestigationPriceRepository priceRepository;


    public InvestigationBookingService(InvestigationBookingRepository investigationBookingRepository, PatientRepository patientRepository,
                                       InvestigationRepository investigationRepository, OrganizationRepository organizationRepository, OrgInvestigationPriceRepository priceRepository) {
        this.bookingRepository = investigationBookingRepository;
        this.patientRepository = patientRepository;
        this.investigationRepository = investigationRepository;
        this.organizationRepository = organizationRepository;
        this.priceRepository = priceRepository;
    }

    public List<Investigation> getInvestigations(PageRequest pageRequest) {
        return investigationRepository.findAll(pageRequest).getContent();
    }

    @Transactional
    public InvestigationBookingDTO addBooking(InvestigationBookingDTO investigationBookingDTO) {

        InvestigationBooking book = new InvestigationBooking();

        Optional<Patient> patient = Optional.empty();
        if (investigationBookingDTO.getPid() != null) {
            patient = patientRepository.findById(investigationBookingDTO.getPid());
        }
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

        List<InvestigationDTO> investigationUpdatePrice = new ArrayList<>();
        for (InvestigationDTO i : investigationBookingDTO.getInvestigationList()) {
            OrgInvestigationPrice orgInvestigationPrice = priceRepository.findByOrganizationAndInvestigation(investigationBookingDTO.getOrg_id(), i.getId());
            if (orgInvestigationPrice != null) {
                i.setServiceCharge(orgInvestigationPrice.getServiceCharge());
            } else {
                i.setServiceCharge(investigationRepository.findById(i.getId()).get().getServiceCharge());
            }
            investigationUpdatePrice.add(i);

        }

        List<Double> priceList = investigationUpdatePrice.stream().map(InvestigationDTO::getServiceCharge).toList();
        Double total = priceList.stream().reduce(0.0, Double::sum);

        book.setInvestigationList(investigationUpdatePrice
                .stream()
                .map(investigationDTO -> investigationRepository.findById(investigationDTO.getId()).orElse(null))
                .toList());


        book.setTotal(total);

        InvestigationBookingDTO investigationBookingDTO1 = InvestigationBookingDTO.form(bookingRepository.save(book));
        return form(investigationBookingDTO1);

    }

    private InvestigationBookingDTO form(InvestigationBookingDTO investigationBookingDTO) {
        List<InvestigationDTO> investigationDTOList = investigationBookingDTO.getInvestigationList().stream().peek(
                i -> {
                    OrgInvestigationPrice orgInvestigationPrice = priceRepository.findByOrganizationAndInvestigation(investigationBookingDTO.getOrg_id(), i.getId());
                    if (orgInvestigationPrice != null) {
                        i.setServiceCharge(orgInvestigationPrice.getServiceCharge());
                    }
                }
        ).toList();

        investigationBookingDTO.setInvestigationList(investigationDTOList);

         return investigationBookingDTO;
    }

    public InvestigationBooking getInvestigationBookingById(Long bookInvestigationId) {
        Optional<InvestigationBooking> investigationBooking = bookingRepository.findById(bookInvestigationId);
        return investigationBooking.orElse(null);
    }

    public List<InvestigationBooking> getInvestigationBookingsByOrgId(Long orId, PageRequest pageRequest) {
        return bookingRepository.findByOrganization(orId, pageRequest);
    }


}