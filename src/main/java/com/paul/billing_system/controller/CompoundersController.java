package com.paul.billing_system.controller;

import com.paul.billing_system.dto.CompoundersChamberDTO;
import com.paul.billing_system.dto.CompoundersHospitalDTO;
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

    //create compouders for hospital
    @PostMapping("/addHospitalCompounders")
    public ResponseEntity<?> saveHospitalCompounders(@Valid @RequestBody CompoundersHospitalDTO compoundersHospitalDTO, BindingResult bindingResult, Long id){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CompoundersHospitalDTO compoundersHospitalDTO1 = CompoundersHospitalDTO.form(compoundersServices.saveHospitalCompounders(id,compoundersHospitalDTO));
        return new ResponseEntity<>(compoundersHospitalDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllCompoundersOfHospital")
    public ResponseEntity<?> getAllCompoundersOfHopitals(){
        List<Compounders> staffs = compoundersServices.getAllCompoundersOfHopitals();
        List<CompoundersHospitalDTO> staffsDTOList = staffs.stream()
                .map(CompoundersHospitalDTO::form).toList();
        return new ResponseEntity<>(staffsDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateCompounderOfHospital/{id}")
    public ResponseEntity<?> updateCompounderOfHospital(@Valid @RequestBody CompoundersHospitalDTO compoundersHospitalDTO, @PathVariable Long id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CompoundersHospitalDTO staffsDTO1 = CompoundersHospitalDTO.form(compoundersServices.updateCompounderOfHospital(compoundersHospitalDTO,id));
        return new ResponseEntity<>(staffsDTO1,HttpStatus.OK);
    }

    //create compouders for chamber
    @PostMapping("/addChamberCompounders")
    public ResponseEntity<?> saveChamberCoumpounder(@Valid @RequestBody CompoundersChamberDTO compoundersChamberDTO, Long id){
        CompoundersChamberDTO compoundersChamberDTO1 = CompoundersChamberDTO.form(compoundersServices.saveChamberCoumpounder(id,compoundersChamberDTO));
        return new ResponseEntity<>(compoundersChamberDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllCompoundersOfChamber")
    public ResponseEntity<?> getAllCompoundersOfChamber(){
        List<Compounders> compounders = compoundersServices.getAllCompoundersOfChamber();
        List<CompoundersChamberDTO> compoundersChamberDTOList = compounders.stream()
                .map(CompoundersChamberDTO::form).toList();
        return new ResponseEntity<>(compoundersChamberDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateCompounderOfChamber/{id}")
    public ResponseEntity<?> updateCompounderOfChamber(@Valid @RequestBody CompoundersChamberDTO compoundersChamberDTO, @PathVariable Long id){

        CompoundersChamberDTO staffsDTO1 = CompoundersChamberDTO.form(compoundersServices.updateCompounderOfChamber(compoundersChamberDTO,id));
        return new ResponseEntity<>(staffsDTO1,HttpStatus.OK);
    }
}
