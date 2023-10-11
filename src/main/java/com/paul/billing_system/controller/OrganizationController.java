package com.paul.billing_system.controller;

import com.paul.billing_system.dto.OrganizationDTO;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import com.paul.billing_system.exception.EmailAlreadyTakenException;
import com.paul.billing_system.exception.OrgCodeIsAlreadyTakenException;
import com.paul.billing_system.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/organization")
@PreAuthorize("hasAnyAuthority('ROLE_ROOT','ROLE_ORG_ADMIN','ROLE_ADMIN')")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @PostMapping("/create")
    public ResponseEntity<?> save(@Valid @RequestBody OrganizationDTO organizationDTO) throws OrgCodeIsAlreadyTakenException, EmailAlreadyTakenException {
        if (organizationService.getSpecialtyByOrgCode(organizationDTO.getOrgCode()).isPresent()) {
            throw new OrgCodeIsAlreadyTakenException(organizationDTO.getOrgCode() + " isn't available!!!");
        }
        if (organizationService.getOrganizationByEmail(organizationDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException(organizationDTO.getEmail() + " isn't Already been Used. Try Another Email.");
        }

        OrganizationDTO organizationDTO1 = OrganizationDTO.form(organizationService.save(organizationDTO));
        return new ResponseEntity<>(organizationDTO1, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @PutMapping("/updateOrganizationProfile/{id}")
    public ResponseEntity<?> updateOrganizationProfile(@Valid @PathVariable Long id, @RequestBody OrganizationDTO organizationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        OrganizationDTO organizationDTO1 = OrganizationDTO.form(organizationService.updateOrganizationProfile(organizationDTO, id));
        return new ResponseEntity<>(organizationDTO1, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @GetMapping("/OrganizationType")
    public List<String> getAllOrganizationTypesList() {
        return OrganizationTypes.getAllOrganizationTypesList();
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @GetMapping("/{type}")
    public ResponseEntity<?> getAllOrganization(@PathVariable String type,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        List<Organization> organizations = organizationService.getAllOrganization(OrganizationTypes.getOrganizationTypeByLabel(type), PageRequest.of(page, size));
        List<OrganizationDTO> organizationDTOList = organizations.stream()
                .map(OrganizationDTO::form)
                .collect(Collectors.toList());
        return new ResponseEntity<>(organizationDTOList, HttpStatus.OK);
    }

    @GetMapping("/getOrganizationById/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Long id) {
        OrganizationDTO organizationDTO = OrganizationDTO.form(organizationService.getOrganizationById(id));
        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
    }

    @GetMapping("/search/{type}/{name}")
    public ResponseEntity<?> searchByNameAndType(@PathVariable String type,
                                                 @PathVariable String name,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(organizationService.searchOrgByNameAndType(OrganizationTypes.getOrganizationTypeByLabel(type), name, PageRequest.of(page, size))
                .stream()
                .map(OrganizationDTO::form)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/getCreationTime/{id}")
    public ResponseEntity<?> getCreationTime(@PathVariable Long id) {
        return new ResponseEntity<>(organizationService.getCreationTime(id).toString(), HttpStatus.OK);
    }

    @GetMapping("/countOrganizationByType/{type}")
    public Long countOrganizationByType(@PathVariable String type) {

        return organizationService.countOrganizationByType(OrganizationTypes.getOrganizationTypeByLabel(type));
    }
}