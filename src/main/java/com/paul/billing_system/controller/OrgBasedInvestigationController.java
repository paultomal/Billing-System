package com.paul.billing_system.controller;

import com.paul.billing_system.dto.OrgInvestigationDTO;
import com.paul.billing_system.service.OrgBasedInvestigationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

