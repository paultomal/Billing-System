package com.paul.billing_system.controller;

import com.paul.billing_system.dto.CompoundersDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.service.CompoundersServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompoundersController {
    private final CompoundersServices compoundersServices;

    public CompoundersController(CompoundersServices compoundersServices) {
        this.compoundersServices = compoundersServices;
    }

    @PostMapping("/addCompounders")
    public ResponseEntity<?> saveStaffs(@RequestBody CompoundersDTO compoundersDTO){
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
    public ResponseEntity<?> updateStaff(@RequestBody CompoundersDTO compoundersDTO, @PathVariable Long id){
        CompoundersDTO staffsDTO1 = CompoundersDTO.form(compoundersServices.updateStaff(compoundersDTO,id));
        return new ResponseEntity<>(staffsDTO1,HttpStatus.OK);
    }
}
