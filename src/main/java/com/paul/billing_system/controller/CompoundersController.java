package com.paul.billing_system.controller;

import com.paul.billing_system.dto.CompoundersChamberDTO;
import com.paul.billing_system.dto.CompoundersHospitalDTO;
import com.paul.billing_system.entity.Compounders;
import com.paul.billing_system.enums.OrganizationTypes;
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
    @PostMapping("/addHospitalCompounders/{id}")
    public ResponseEntity<?> saveHospitalCompounders(@Valid @RequestBody CompoundersHospitalDTO compoundersHospitalDTO, BindingResult bindingResult,@PathVariable Long id){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CompoundersHospitalDTO compoundersHospitalDTO1 = CompoundersHospitalDTO.form(compoundersServices.saveHospitalCompounders(id,compoundersHospitalDTO));
        return new ResponseEntity<>(compoundersHospitalDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllCompoundersOfHospital/{id}")
    public ResponseEntity<?> getAllCompoundersOfHopitals(@PathVariable Long id){
        List<Compounders> compounders = compoundersServices.getAllCompoundersOfHospitals(id);
        List<CompoundersHospitalDTO> compoundersHospitalDTOList = compounders.stream()
                .map(CompoundersHospitalDTO::form).toList();
        return new ResponseEntity<>(compoundersHospitalDTOList,HttpStatus.OK);
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
    @PostMapping("/addChamberCompounders/{id}")
    public ResponseEntity<?> saveChamberCoumpounder(@Valid @RequestBody CompoundersChamberDTO compoundersChamberDTO,@PathVariable Long id){

        CompoundersChamberDTO compoundersChamberDTO1 = CompoundersChamberDTO.form(compoundersServices.saveChamberCoumpounder(id,compoundersChamberDTO));
        return new ResponseEntity<>(compoundersChamberDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllCompoundersOfChamber/{id}")
    public ResponseEntity<?> getAllCompoundersOfChamber(@PathVariable Long id){
        List<Compounders> compounders = compoundersServices.getAllCompoundersOfChamber(id);
        List<CompoundersChamberDTO> compoundersChamberDTOList = compounders.stream()
                .map(CompoundersChamberDTO::form).toList();
        return new ResponseEntity<>(compoundersChamberDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateCompounderOfChamber/{id}")
    public ResponseEntity<?> updateCompounderOfChamber(@Valid @RequestBody CompoundersChamberDTO compoundersChamberDTO, @PathVariable Long id){

        CompoundersChamberDTO staffsDTO1 = CompoundersChamberDTO.form(compoundersServices.updateCompounderOfChamber(compoundersChamberDTO,id));
        return new ResponseEntity<>(staffsDTO1,HttpStatus.OK);
    }

    @GetMapping("/getCompounderOfChamberById/{id}")
    public ResponseEntity<?> getCompounderById(@PathVariable Long id){
        CompoundersChamberDTO compoundersChamberDTO = CompoundersChamberDTO.form(compoundersServices.getCompounderById(id));
        return new ResponseEntity<>(compoundersChamberDTO,HttpStatus.OK);
    }
}
