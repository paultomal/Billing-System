package com.paul.billing_system.controller;

import com.paul.billing_system.dto.PatientDTO;
import com.paul.billing_system.entity.Patient;
import com.paul.billing_system.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/patient")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class PatientsController {
    private final PatientService patientServices;

    public PatientsController(PatientService patientServices) {
        this.patientServices = patientServices;
    }

    @PostMapping("/addPatients/{id}")
    public ResponseEntity<?> savePatients(@Valid @RequestBody PatientDTO patientDTO,
                                          @PathVariable Long id, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        PatientDTO patientDTO1 = PatientDTO.form(patientServices.savePatients(id, patientDTO));
        return new ResponseEntity<>(patientDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllPatients/{id}")
    public ResponseEntity<?> getAllPatients(@PathVariable Long id,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        List<Patient> patients = patientServices.getAllPatients(id, PageRequest.of(page, size));
        List<PatientDTO> patientDTOList = patients.stream().map(PatientDTO::form).toList();
        return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
    }

    @GetMapping("/getPatient/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        PatientDTO patientDTO = PatientDTO.form(patientServices.getPatientById(id));
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<?> updatePatient(@Valid @PathVariable Long id, @RequestBody PatientDTO patientDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        PatientDTO patientDTO1 = PatientDTO.form(patientServices.updatePatient(patientDTO, id));
        return new ResponseEntity<>(patientDTO1, HttpStatus.OK);
    }

    @GetMapping("search/{orgId}/{name}")
    public ResponseEntity<?> searchPatient(@PathVariable Long orgId,
                                           @PathVariable String name,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {

        return new ResponseEntity<>(patientServices.searchPatient(orgId, name, PageRequest.of(page, size))
                .stream()
                .map(PatientDTO::form)
                .toList(), HttpStatus.OK);
    }
}
