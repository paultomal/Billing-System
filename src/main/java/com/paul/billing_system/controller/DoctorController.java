package com.paul.billing_system.controller;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.service.DoctorServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorServices doctorServices;

    public DoctorController(DoctorServices doctorServices) {
        this.doctorServices = doctorServices;
    }

    @PostMapping("/addDoctor/{id}")
    public ResponseEntity<?> save(@RequestBody DoctorDTO doctorDTO, @PathVariable Long id){
        DoctorDTO doctorDTO1 = DoctorDTO.form(doctorServices.save(id,doctorDTO));
        return new ResponseEntity<>(doctorDTO1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id){
        DoctorDTO doctorDTO = DoctorDTO.form(doctorServices.getDoctorById(id));
        return new ResponseEntity<>(doctorDTO,HttpStatus.OK);
    }

    @GetMapping("/getAllDoctors")
    public ResponseEntity<?> getAllDoctors(){
        List<Doctors> doctors = doctorServices.getAllDoctors();
        List<DoctorDTO> doctorDTOList = doctors.stream().map(DoctorDTO::form).toList();
        return new ResponseEntity<>(doctorDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateDoctor/{id}")
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorDTO doctorDTO, @PathVariable Long id){
        DoctorDTO doctorDTO1 = DoctorDTO.form(doctorServices.updateDoctor(id,doctorDTO));
        return new ResponseEntity<>(doctorDTO1,HttpStatus.OK);
    }
}
