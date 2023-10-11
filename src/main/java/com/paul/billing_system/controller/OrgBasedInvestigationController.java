package com.paul.billing_system.controller;

import com.paul.billing_system.dto.OrgInvestigationDTO;
import com.paul.billing_system.service.OrgBasedInvestigationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orgInvestigation")
@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
public class OrgBasedInvestigationController {
    private final OrgBasedInvestigationService orgBasedInvestigationService;

    public OrgBasedInvestigationController(OrgBasedInvestigationService orgBasedInvestigationService) {
        this.orgBasedInvestigationService = orgBasedInvestigationService;
    }

    @PutMapping("/updateInvestigationOrgPrice")
    public OrgInvestigationDTO updateInvestigationPrice(@RequestBody OrgInvestigationDTO orgInvestigationDTO) {
        return orgBasedInvestigationService.updateInvestigationOrgPrice(orgInvestigationDTO);
    }


}


























/*
    @PutMapping("/updateInvestigation")
    public ResponseEntity<?> addOrgInvestigation(@RequestBody OrgInvestigationDTO orgInvestigationDTO) {
        OrgInvestigationDTO orgInvestigationDTO1 = OrgInvestigationDTO.form(orgBasedInvestigationService.addOrgInvestigation(orgInvestigationDTO));
        return new ResponseEntity<>(orgInvestigationDTO1, HttpStatus.OK);
    }
*/

