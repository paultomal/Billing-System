package com.paul.billing_system.controller;

import com.paul.billing_system.dto.CompoundersHospitalDTO;
import com.paul.billing_system.dto.OrganizationDTO;
import com.paul.billing_system.entity.Compounders;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationServices organizationServices;

    public OrganizationController(OrganizationServices organizationServices) {
        this.organizationServices = organizationServices;
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@Valid @RequestBody OrganizationDTO organizationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        OrganizationDTO organizationDTO1 = OrganizationDTO.form(organizationServices.save(organizationDTO));
        return new ResponseEntity<>(organizationDTO1, HttpStatus.OK);
    }

    @GetMapping("/OrganizationType")
    public List<String> getAllOrganizationTypesList() {
        return OrganizationTypes.getAllOrganizationTypesList();
    }

    @GetMapping("/{type}")
    public ResponseEntity<?> getAllOrganization(@PathVariable String type){
        List<Organization> organizations =  organizationServices.getAllOrganization(OrganizationTypes.getOrganizationTypeByLabel(type));
        List<OrganizationDTO> organizationDTOList = organizations.stream()
                .map(OrganizationDTO::form)
                .collect(Collectors.toList());
/*        List<String> organizationName = organizations.stream()
                .map(Organization::getName).toList();*/
        return new ResponseEntity<>(organizationDTOList,HttpStatus.OK);
    }

    @PutMapping("/updateOrganizationProfile/{id}")
    public ResponseEntity<?> updateOrganizationProfile(@Valid @PathVariable Long id, @RequestBody OrganizationDTO organizationDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        OrganizationDTO organizationDTO1 = OrganizationDTO.form(organizationServices.updateOrganizationProfile(organizationDTO,id));
        return new ResponseEntity<>(organizationDTO1,HttpStatus.OK);
    }

    @GetMapping("/getOrganizationById/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Long id){
        OrganizationDTO organizationDTO = OrganizationDTO.form(organizationServices.getOrganizationByid(id));
        return new ResponseEntity<>(organizationDTO,HttpStatus.OK);
    }

    /*    @GetMapping()
        public ResponseEntity<?> getAllCompoundersByOrgId(@PathVariable Long id){
            Organization organization = organizationServices.getOrganizationByid(id);
            List<Compounders> compounders = organization.getCompounders();
            List<CompoundersHospitalDTO> compoundersHospitalDTOList = compounders.stream().map()
        }*/
}
