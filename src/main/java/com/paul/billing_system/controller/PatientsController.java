package com.paul.billing_system.controller;

import com.paul.billing_system.dto.PatientsDTO;
import com.paul.billing_system.entity.Patients;
import com.paul.billing_system.service.PatientsServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientsController {
    private final PatientsServices patientsServices;

    public PatientsController(PatientsServices patientsServices) {
        this.patientsServices = patientsServices;
    }

    @PostMapping("/addPatients")
    public ResponseEntity<?> savePatients(@Valid @RequestBody PatientsDTO patientsDTO , BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        PatientsDTO patientsDTO1 = PatientsDTO.form(patientsServices.savePatients(patientsDTO));
        return new ResponseEntity<>(patientsDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<?> getAllPatients(){
        List<Patients> patients = patientsServices.getAllPatients();
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
