package com.paul.billing_system.controller;

import com.paul.billing_system.dto.SpecialistDTO;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.service.SpecialistServices;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specialist")
@PreAuthorize("hasAuthority('ROLE_ROOT')")
public class SpecialistController {
    private final SpecialistServices specialistServices;

    public SpecialistController(SpecialistServices specialistServices) {
        this.specialistServices = specialistServices;
    }

    @GetMapping("/getAllSpecialist/{offset}/{pageSize}")
    public ResponseEntity<?> getAllSpecialist(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Specialist> specialists = specialistServices.getAllSpecialist(offset, pageSize);
        List<SpecialistDTO> specialistDTOList = specialists.stream().map(SpecialistDTO::form).toList();
        return new ResponseEntity<>(specialistDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicalSpecialist(@PathVariable Long id) {
        SpecialistDTO specialistDTO = SpecialistDTO.form(specialistServices.getMedicalSpecialist(id));
        return new ResponseEntity<>(specialistDTO, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchSpecialist(@PathVariable String name) {
        List<Specialist> specialists = specialistServices.searchSpecialist(name);
        List<SpecialistDTO> specialistDTOList = specialists.stream().map(SpecialistDTO::form).toList();
        return new ResponseEntity<>(specialistDTOList, HttpStatus.OK);
    }
}
