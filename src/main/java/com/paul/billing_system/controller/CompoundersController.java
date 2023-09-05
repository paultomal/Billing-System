package com.paul.billing_system.controller;

import com.paul.billing_system.dto.CompoundersDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.service.CompoundersServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompoundersController {
    private final CompoundersServices compoundersServices;

    public CompoundersController(CompoundersServices compoundersServices) {
        this.compoundersServices = compoundersServices;
    }

    @PostMapping("/addCompounders")
    public ResponseEntity<?> saveStaffs(@Valid @RequestBody CompoundersDTO compoundersDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CompoundersDTO staffsDTO1 = CompoundersDTO.form(compoundersServices.saveStaffs(compoundersDTO));
        return new ResponseEntity<>(staffsDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllCompounders")
    public ResponseEntity<?> getAllStaffs(){
        List<Compounders> staffs = compoundersServices.getAllStaffs();
        List<CompoundersDTO> staffsDTOList = staffs.stream()
                .map(CompoundersDTO::form).toList();
        return new ResponseEntity<>(staffsDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateCompounder/{id}")
    public ResponseEntity<?> updateStaff(@Valid @RequestBody CompoundersDTO compoundersDTO, @PathVariable Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CompoundersDTO staffsDTO1 = CompoundersDTO.form(compoundersServices.updateStaff(compoundersDTO,id));
        return new ResponseEntity<>(staffsDTO1,HttpStatus.OK);
    }
}
