package com.paul.billing_system.controller;

import com.paul.billing_system.dto.InvestigationBookingDTO;
import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.InvestigationBooking;
import com.paul.billing_system.entity.Patient;
import com.paul.billing_system.service.InvestigationBookingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/bookInvestigation")
public class InvestigationBookingController {

    private final InvestigationBookingService bookingServices;

    public InvestigationBookingController(InvestigationBookingService bookingServices) {
        this.bookingServices = bookingServices;
    }

    @PostMapping("/addBooking")
    public ResponseEntity<?> addBooking(@RequestBody InvestigationBookingDTO investigationBookingDTO) {
        return new ResponseEntity<>(bookingServices.addBooking(investigationBookingDTO), HttpStatus.OK);
    }

    @GetMapping("/getInvestigationBookingById/{book_investigation_id}")
    public ResponseEntity<?> getInvestigationById(@PathVariable Long book_investigation_id) {
        InvestigationBookingDTO investigationBookingDTO = InvestigationBookingDTO.form(bookingServices.getInvestigationBookingById(book_investigation_id));
        return new ResponseEntity<>(investigationBookingDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getInvestigationBookingsByOrgId/{orId}")
    public ResponseEntity<?> getInvestigationBookingsByOrgId(@PathVariable Long orId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        List<InvestigationBooking> investigationBookings = bookingServices.getInvestigationBookingsByOrgId(orId, PageRequest.of(page, size));
        List<InvestigationBookingDTO> investigationBookingDTOList = investigationBookings.stream().map(InvestigationBookingDTO::form).toList();
        return new ResponseEntity<>(investigationBookingDTOList, HttpStatus.OK);
    }

    @GetMapping("/getInvestigationList")
    public ResponseEntity<?> getInvestigation(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        List<Investigation> investigations = bookingServices.getInvestigations(PageRequest.of(page, size));
        List<InvestigationDTO> investigationDTOList = investigations
                .stream()
                .map(InvestigationDTO::form)
                .toList();
        return new ResponseEntity<>(investigationDTOList, HttpStatus.OK);
    }

    @GetMapping("/search/{orgId}/{patientName}")
    public ResponseEntity<?> searchBookInvestigation(@PathVariable Long orgId,
                                                     @PathVariable String patientName,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        List<InvestigationBooking> investigations = bookingServices.searchBookInvestigation(orgId,patientName, PageRequest.of(page, size));
        List<InvestigationBookingDTO> investigationList = investigations.stream().map(InvestigationBookingDTO::form).toList();
        return new ResponseEntity<>(investigationList,HttpStatus.OK);
    }
}