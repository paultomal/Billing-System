package com.paul.billing_system.controller;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.entity.Doctor;
import com.paul.billing_system.enums.DaysOfWeek;
import com.paul.billing_system.service.DoctorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/doctors")
//@PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN')")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/addDoctor/{orgId}")
    public ResponseEntity<?> save(@RequestBody DoctorDTO doctorDTO, @PathVariable Long orgId, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        DoctorDTO doctorDTO1 = DoctorDTO.form(doctorService.save(orgId, doctorDTO));
        return new ResponseEntity<>(doctorDTO1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        DoctorDTO doctorDTO = DoctorDTO.form(doctorService.getDoctorById(id));
        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }

    @GetMapping("/Days")
    public List<String> getAllOrganizationTypesList() {
        return DaysOfWeek.getAllDaysOfWeek();
    }

    @GetMapping("/getAllDoctors/{id}/{sId}")
    public ResponseEntity<?> getAllDoctors(@PathVariable Long id, @PathVariable Long sId,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        List<Doctor> doctors = doctorService.getAllDoctors(id, sId, PageRequest.of(page,size));
        List<DoctorDTO> doctorDTOList = doctors.stream().map(DoctorDTO::form).toList();
        return new ResponseEntity<>(doctorDTOList, HttpStatus.OK);
    }

    @PutMapping("/updateDoctor/{id}")
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorDTO doctorDTO, @PathVariable Long id, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        DoctorDTO doctorDTO1 = DoctorDTO.form(doctorService.updateDoctor(id, doctorDTO));
        return new ResponseEntity<>(doctorDTO1, HttpStatus.OK);
    }

    @GetMapping("/searchDoctor/{name}")
    public ResponseEntity<?> searchDoctorByName(@PathVariable String name,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(doctorService.searchDoctor(name, PageRequest.of(page,size))
                .stream()
                .map(DoctorDTO::form)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/searchDoctorByOrg/{orgId}/{name}")
    public ResponseEntity<?> searchDoctorByName(@PathVariable Long orgId,
                                                @PathVariable String name,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(doctorService.searchDoctorUnderOrg(orgId, name, PageRequest.of(page,size))
                .stream()
                .map(DoctorDTO::form)
                .toList(), HttpStatus.OK);
    }
}
