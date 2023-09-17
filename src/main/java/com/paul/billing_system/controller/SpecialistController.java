package com.paul.billing_system.controller;

import com.paul.billing_system.dto.SpecialistDTO;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.service.SpecialistServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/specialist")
@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
public class SpecialistController {
    private final SpecialistServices specialistServices;

    public SpecialistController(SpecialistServices specialistServices) {
        this.specialistServices = specialistServices;
    }

    @PostMapping("/addSpecialist/{id}")
    public ResponseEntity<?> saveMedSpecialist(@RequestBody SpecialistDTO specialistDTO, @PathVariable Long id, BindingResult bindingResult){
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        SpecialistDTO specialistDTO1 = SpecialistDTO.form(specialistServices.saveSpecialist(id, specialistDTO));
        return new ResponseEntity<>(specialistDTO1, HttpStatus.OK);
    }
    @GetMapping("/getAllSpecialist")
    public ResponseEntity<?> getAllMedSpecialist(){
        List<Specialist> specialists = specialistServices.getAllMedSpecialist();
        List<SpecialistDTO> specialistDTOList = specialists.stream().map(SpecialistDTO::form).toList();
        return new ResponseEntity<>(specialistDTOList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicalSpecialist(@PathVariable Long id){
        SpecialistDTO specialistDTO = SpecialistDTO.form(specialistServices.getMedicalSpecialist(id));
        return new ResponseEntity<>(specialistDTO,HttpStatus.OK);
    }

}
