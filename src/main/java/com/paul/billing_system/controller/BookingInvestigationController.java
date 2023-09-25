package com.paul.billing_system.controller;

import com.paul.billing_system.dto.BookingInvestigationDTO;
import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.dto.PatientsDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.service.BookingInvestigationServices;
import com.paul.billing_system.service.PatientsServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@RequestMapping("/bookInvestigation")
public class BookingInvestigationController {

    private final BookingInvestigationServices bookingServices;

    public BookingInvestigationController(BookingInvestigationServices bookingServices) {
        this.bookingServices = bookingServices;
    }

    @GetMapping("/searchPatient/{name}")
    public ResponseEntity<?> getPatients(@PathVariable String name, @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size){
        List<Patients> patients = bookingServices.searchPatient(name, PageRequest.of(page, size));
        List<PatientsDTO> patientsDTOList = patients.stream().map(PatientsDTO::form).toList();
        return new ResponseEntity<>(patientsDTOList, HttpStatus.OK);
    }

    @GetMapping("/getInvestigationList/{id}/{spId}")
    public ResponseEntity<?> getInvestigation( @PathVariable Long id,@PathVariable Long spId){
        List<Investigation> investigations = bookingServices.getInvestigations(id,spId);
        List<InvestigationDTO> investigationDTOList = investigations
                .stream()
                .map(InvestigationDTO::form)
                .toList();
        return new ResponseEntity<>(investigationDTOList,HttpStatus.OK);
    }

    @PostMapping("/addBooking")
    public ResponseEntity<?> addBooking(@RequestBody BookingInvestigationDTO bookingInvestigationDTO){
        BookingInvestigationDTO bookingInvestigationDTO1 = BookingInvestigationDTO.form(bookingServices.addBooking(bookingInvestigationDTO));
        return new ResponseEntity<>(bookingInvestigationDTO1,HttpStatus.OK);
    }
}