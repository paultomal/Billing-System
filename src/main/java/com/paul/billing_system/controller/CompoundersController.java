package com.paul.billing_system.controller;

import com.paul.billing_system.dto.CompoundersDTO;
import com.paul.billing_system.service.CompoundersServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/compounder")
@PreAuthorize("hasAuthority('ROLE_Admin')")

public class CompoundersController {
    private final CompoundersServices compoundersServices;

    public CompoundersController(CompoundersServices compoundersServices) {
        this.compoundersServices = compoundersServices;
    }

    @PostMapping("/addCompounders/{id}")
    public ResponseEntity<?> saveCompounders(@Valid @RequestBody CompoundersDTO compoundersDTO, BindingResult bindingResult, @PathVariable Long id) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        CompoundersDTO compoundersDTO1 = CompoundersDTO.form(compoundersServices.saveCompounders(id, compoundersDTO));
        return new ResponseEntity<>(compoundersDTO1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompounderById(@PathVariable Long id) {
        CompoundersDTO compoundersDTO = CompoundersDTO.form(compoundersServices.getCompounderById(id));
        return new ResponseEntity<>(compoundersDTO, HttpStatus.OK);
    }


    @PutMapping("/updateCompounder/{id}")
    public ResponseEntity<?> updateCompounder(@Valid @RequestBody CompoundersDTO compoundersDTO, @PathVariable Long id, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        CompoundersDTO staffsDTO1 = CompoundersDTO.form(compoundersServices.updateCompounder(compoundersDTO, id));
        return new ResponseEntity<>(staffsDTO1, HttpStatus.OK);
    }












/*    @GetMapping("/getAllCompounders/{id}")
    public ResponseEntity<?> getAllCompounders(@PathVariable Long id){
        List<Compounders> compounders = compoundersServices.getAllCompounders(id);
        List<CompoundersDTO> compoundersDTOList = compounders.stream()
                .map(CompoundersDTO::form)
                .toList();
        return new ResponseEntity<>(compoundersDTOList,HttpStatus.OK);
    }*/
/*    //create compouders for chamber
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
    }*/
}
