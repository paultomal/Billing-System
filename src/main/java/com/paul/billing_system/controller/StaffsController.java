package com.paul.billing_system.controller;

import com.paul.billing_system.dto.StaffsDTO;
import com.paul.billing_system.entity.Staffs;
import com.paul.billing_system.service.StaffsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StaffsController {
    private final StaffsServices staffsServices;

    public StaffsController(StaffsServices staffsServices) {
        this.staffsServices = staffsServices;
    }

    @PostMapping("/addStaffs")
    public ResponseEntity<?> saveStaffs(@RequestBody StaffsDTO staffsDTO){
        StaffsDTO staffsDTO1 = StaffsDTO.form(staffsServices.saveStaffs(staffsDTO));
        return new ResponseEntity<>(staffsDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllStaffs")
    public ResponseEntity<?> getAllStaffs(){
        List<Staffs> staffs = staffsServices.getAllStaffs();
        List<StaffsDTO> staffsDTOList = staffs.stream()
                .map(StaffsDTO::form).toList();
        return new ResponseEntity<>(staffsDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateStaff/{id}")
    public ResponseEntity<?> updateStaff(@RequestBody StaffsDTO staffsDTO, @PathVariable Long id){
        StaffsDTO staffsDTO1 = StaffsDTO.form(staffsServices.updateStaff(staffsDTO,id));
        return new ResponseEntity<>(staffsDTO1,HttpStatus.OK);
    }
}
