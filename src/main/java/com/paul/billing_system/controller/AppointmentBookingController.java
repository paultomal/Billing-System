package com.paul.billing_system.controller;

import com.paul.billing_system.dto.AppointmentBookingDTO;
import com.paul.billing_system.service.AppointmentBookingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN','ROLE_ROOT')")
public class AppointmentBookingController {
    private final AppointmentBookingService appointmentBookingService;

    public AppointmentBookingController(AppointmentBookingService appointmentBookingService) {
        this.appointmentBookingService = appointmentBookingService;
    }

    @PostMapping("/makeAppointment")
    public ResponseEntity<?> makeAppointment(@Valid @RequestBody AppointmentBookingDTO appointmentBookingDTO) {
        return new ResponseEntity<>(appointmentBookingService.makeAppointment(appointmentBookingDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAppointment/{id}")
    public AppointmentBookingDTO getAppointment(@PathVariable Long id) {
        return appointmentBookingService.getAppointment(id);
    }

    @GetMapping("/getAppointmentsByOrg/{orgId}")
    public List<AppointmentBookingDTO> getAppointmentsByOrg(@PathVariable Long orgId,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return appointmentBookingService.getAppointmentsByOrg(orgId, PageRequest.of(page, size));
    }

    @GetMapping("/getRevenue/{orgId}")
    public ResponseEntity<?> getRevenueOfHospital(@PathVariable Long orgId) {
        return new ResponseEntity<>(appointmentBookingService.getTotalRevenueForHospital(orgId), HttpStatus.OK);
    }
}
