package com.paul.billing_system.controller;

import com.paul.billing_system.dto.OrganizationDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.service.OrganizationServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api/organization")
public class OrganizationController {
    private final OrganizationServices organizationServices;

    public OrganizationController(OrganizationServices organizationServices) {
        this.organizationServices = organizationServices;
    }

    @PostMapping("/organization/create")
    public ResponseEntity<?> save(@Valid @RequestBody OrganizationDTO organizationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError fieldError) {
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(errorMessage);
        }
        OrganizationDTO organizationDTO1 = OrganizationDTO.form(organizationServices.save(organizationDTO));
        return new ResponseEntity<>(organizationDTO1, HttpStatus.OK);
    }
    @GetMapping("/OrganizationType")
    public List<String> getAllOrganizationTypesList() {
        return OrganizationTypes.getAllOrganizationTypesList();
    }

    @GetMapping("/organization/list")
    public ResponseEntity<?> getAllOrganization(){
        List<Organization> organizations =  organizationServices.getAllOrganization();
       /* List<OrganizationDTO> organizationDTOList = organizations.stream()
                .map(OrganizationDTO::form)
                .collect(Collectors.toList());*/
        List<String> organizationName = organizations.stream()
                .map(Organization::getName).toList();
        return new ResponseEntity<>(organizationName,HttpStatus.OK);
    }

    @GetMapping("/organization/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Long id){
        OrganizationDTO organizationDTO = OrganizationDTO.form(organizationServices.getOrganizationByid(id));
        return new ResponseEntity<>(organizationDTO,HttpStatus.OK);
    }
    @PutMapping("/updateOrganizationProfile/{id}")
    public ResponseEntity<?> updateOrganizationProfile(@Valid @PathVariable Long id, @RequestBody OrganizationDTO organizationDTO){
        OrganizationDTO organizationDTO1 = OrganizationDTO.form(organizationServices.updateOrganizationProfile(organizationDTO,id));
        return new ResponseEntity<>(organizationDTO1,HttpStatus.OK);
    }
}
