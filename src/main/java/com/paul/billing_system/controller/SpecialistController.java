package com.paul.billing_system.controller;

import com.paul.billing_system.dto.SpecialistDTO;
import com.paul.billing_system.entity.Specialist;
import com.paul.billing_system.service.SpecialistServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialist")
@PreAuthorize("hasAuthority('ROLE_ROOT')")
public class SpecialistController {
    private final SpecialistServices specialistServices;

    public SpecialistController(SpecialistServices specialistServices) {
        this.specialistServices = specialistServices;
    }

    @GetMapping("/getAllSpecialist")
    public ResponseEntity<?> getAllSpecialist(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Specialist> specialists = specialistServices.getAllSpecialist(PageRequest.of(page, size));
        List<SpecialistDTO> specialistDTOList = specialists.stream().map(SpecialistDTO::form).toList();
        return new ResponseEntity<>(specialistDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicalSpecialist(@PathVariable Long id) {
        SpecialistDTO specialistDTO = SpecialistDTO.form(specialistServices.getMedicalSpecialist(id));
        return new ResponseEntity<>(specialistDTO, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchSpecialist(@PathVariable String name,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        List<Specialist> specialists = specialistServices.searchSpecialist(name, PageRequest.of(page, size));
        List<SpecialistDTO> specialistDTOList = specialists.stream().map(SpecialistDTO::form).toList();
        return new ResponseEntity<>(specialistDTOList, HttpStatus.OK);
    }
}
