package com.paul.billing_system.controller;

import com.paul.billing_system.dto.SpecialityDTO;
import com.paul.billing_system.entity.Speciality;
import com.paul.billing_system.service.SpecialityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialist")
public class SpecialityController {
    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping("/getAllSpecialist")
    public ResponseEntity<?> getAllSpecialist(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<Speciality> specialists = specialityService.getAllSpecialist(PageRequest.of(page, size));
        List<SpecialityDTO> specialityDTOList = specialists
                .stream()
                .map(SpecialityDTO::form)
                .toList();
        return new ResponseEntity<>(specialityDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicalSpecialist(@PathVariable Long id) {
        SpecialityDTO specialityDTO = SpecialityDTO.form(specialityService.getMedicalSpecialist(id));
        return new ResponseEntity<>(specialityDTO, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchSpecialist(@PathVariable String name,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        List<Speciality> specialities = specialityService.searchSpecialist(name, PageRequest.of(page, size));
        List<SpecialityDTO> specialityDTOList = specialities.stream().map(SpecialityDTO::form).toList();
        return new ResponseEntity<>(specialityDTOList, HttpStatus.OK);
    }
}
