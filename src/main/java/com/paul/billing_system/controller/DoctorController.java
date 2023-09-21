package com.paul.billing_system.controller;

import com.paul.billing_system.dto.DoctorDTO;
import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.enums.DaysOfWeek;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.service.DoctorServices;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/doctors")
@PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN')")
public class DoctorController {
    private final DoctorServices doctorServices;

    public DoctorController(DoctorServices doctorServices) {
        this.doctorServices = doctorServices;
    }

    @PostMapping("/addDoctor/{id}")
    public ResponseEntity<?> save(@RequestBody DoctorDTO doctorDTO, @PathVariable Long id, BindingResult bindingResult){
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        DoctorDTO doctorDTO1 = DoctorDTO.form(doctorServices.save(id,doctorDTO));
        return new ResponseEntity<>(doctorDTO1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id){
        DoctorDTO doctorDTO = DoctorDTO.form(doctorServices.getDoctorById(id));
        return new ResponseEntity<>(doctorDTO,HttpStatus.OK);
    }
    @GetMapping("/Days")
    public List<String> getAllOrganizationTypesList() {
        return DaysOfWeek.getAllDaysOfWeek();
    }

    @GetMapping("/getAllDoctors/{id}/{sId}")
    public ResponseEntity<?> getAllDoctors(@PathVariable Long id, @PathVariable Long sId ){
        List<Doctors> doctors = doctorServices.getAllDoctors(id,sId);
        List<DoctorDTO> doctorDTOList = doctors.stream().map(DoctorDTO::form).toList();
        return new ResponseEntity<>(doctorDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateDoctor/{id}")
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorDTO doctorDTO, @PathVariable Long id,BindingResult bindingResult){
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        DoctorDTO doctorDTO1 = DoctorDTO.form(doctorServices.updateDoctor(id,doctorDTO));
        return new ResponseEntity<>(doctorDTO1,HttpStatus.OK);
    }
}
