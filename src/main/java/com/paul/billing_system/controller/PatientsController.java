package com.paul.billing_system.controller;

import com.paul.billing_system.dto.PatientsDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.service.PatientsServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/patient")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class PatientsController {
    private final PatientsServices patientsServices;

    public PatientsController(PatientsServices patientsServices) {
        this.patientsServices = patientsServices;
    }

    @PostMapping("/addPatients/{id}")
    public ResponseEntity<?> savePatients(@Valid @RequestBody PatientsDTO patientsDTO ,@PathVariable Long id, BindingResult bindingResult){
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        PatientsDTO patientsDTO1 = PatientsDTO.form(patientsServices.savePatients(id,patientsDTO));
        return new ResponseEntity<>(patientsDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllPatients/{id}/{spId}")
    public ResponseEntity<?> getAllPatients(@PathVariable Long id , @PathVariable Long spId){
        List<Patients> patients = patientsServices.getAllPatients(id,spId);
        List<PatientsDTO> patientsDTOList = patients.stream().map(PatientsDTO::form).toList();
        return new ResponseEntity<>(patientsDTOList,HttpStatus.OK);
    }

    @GetMapping("/getPatient/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id){
        PatientsDTO patientsDTO = PatientsDTO.form(patientsServices.getPatientById(id));
        return new ResponseEntity<>(patientsDTO,HttpStatus.OK);
    }

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<?> updatePatient(@Valid @PathVariable Long id, @RequestBody PatientsDTO patientsDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        PatientsDTO patientsDTO1 = PatientsDTO.form(patientsServices.updatePatient(patientsDTO,id));
        return new ResponseEntity<>(patientsDTO1,HttpStatus.OK);
    }
}
