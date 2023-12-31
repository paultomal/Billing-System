package com.paul.billing_system.controller;

import com.paul.billing_system.dto.SpecialityDTO;
import com.paul.billing_system.entity.Speciality;
import com.paul.billing_system.exception.SpecialtyNameAlreadyTakenException;
import com.paul.billing_system.service.SpecialityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialist")
@PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN','ROLE_ROOT')")
public class SpecialityController {
    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @PostMapping("/addSpeciality")
    public ResponseEntity<?> addSpeciality(@Valid @RequestBody SpecialityDTO specialityDTO) throws SpecialtyNameAlreadyTakenException {
        if (specialityService.getSpecialtyByName(specialityDTO.getMedSpecName()).isPresent()) {
            throw new SpecialtyNameAlreadyTakenException(specialityDTO.getMedSpecName() + " is Already Saved in Database");
        }
        SpecialityDTO specialityDTO1 = SpecialityDTO.form(specialityService.addSpeciality(specialityDTO));
        return new ResponseEntity<>(specialityDTO1, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @PutMapping("/updateSpeciality/{sId}")
    public ResponseEntity<?> updateSpeciality(@PathVariable Long sId, @RequestBody SpecialityDTO specialityDTO) {
        SpecialityDTO specialityDTO1 = SpecialityDTO.form(specialityService.updateSpeciality(sId, specialityDTO));
        return new ResponseEntity<>(specialityDTO1, HttpStatus.OK);
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


    @GetMapping("/countSpeciality")
    public Long countSpeciality() {
        return specialityService.countSpeciality();
    }
}
