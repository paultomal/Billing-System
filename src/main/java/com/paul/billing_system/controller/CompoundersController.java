package com.paul.billing_system.controller;

import com.paul.billing_system.dto.CompoundersDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.service.StaffsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompoundersController {
    private final StaffsServices staffsServices;

    public CompoundersController(StaffsServices staffsServices) {
        this.staffsServices = staffsServices;
    }

    @PostMapping("/addCompounders")
    public ResponseEntity<?> saveStaffs(@RequestBody CompoundersDTO compoundersDTO){
        CompoundersDTO staffsDTO1 = CompoundersDTO.form(staffsServices.saveStaffs(compoundersDTO));
        return new ResponseEntity<>(staffsDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllCompounders")
    public ResponseEntity<?> getAllStaffs(){
        List<Compounders> staffs = staffsServices.getAllStaffs();
        List<CompoundersDTO> staffsDTOList = staffs.stream()
                .map(CompoundersDTO::form).toList();
        return new ResponseEntity<>(staffsDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateCompounder/{id}")
    public ResponseEntity<?> updateStaff(@RequestBody CompoundersDTO compoundersDTO, @PathVariable Long id){
        CompoundersDTO staffsDTO1 = CompoundersDTO.form(staffsServices.updateStaff(compoundersDTO,id));
        return new ResponseEntity<>(staffsDTO1,HttpStatus.OK);
    }
}
