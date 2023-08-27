package com.paul.billing_system.controller;

import com.paul.billing_system.dto.HospitalDTO;
import com.paul.billing_system.entity.Hospital;
import com.paul.billing_system.service.HospitalServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HospitalController {
    private final HospitalServices hospitalServices;

    public HospitalController(HospitalServices hospitalServices) {
        this.hospitalServices = hospitalServices;
    }

    @PostMapping("/hospital/create")
    public ResponseEntity<?> save(@RequestBody HospitalDTO hospitalDTO){
        HospitalDTO hospitalDTO1 = HospitalDTO.hospitalForm(hospitalServices.save(hospitalDTO));
        return new ResponseEntity<>(hospitalDTO1, HttpStatus.OK);
    }

    @GetMapping("/hospital/list")
    public ResponseEntity<?> getAllHospitalList(){
        List<Hospital> hospitals = hospitalServices.getALlHospitalList();
        List<HospitalDTO> hospitalDTOList = hospitals.stream().map(HospitalDTO::hospitalForm).toList();
        return new ResponseEntity<>(hospitalDTOList,HttpStatus.OK);
    }
}
