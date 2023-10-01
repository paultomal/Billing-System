package com.paul.billing_system.controller;

import com.paul.billing_system.dto.OrgBasedInvestigationDTO;
import com.paul.billing_system.entity.OrgBasedInvestigation;
import com.paul.billing_system.service.OrgBasedInvestigationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orgInvestigation")
public class OrgBasedInvestigationController {
    private final OrgBasedInvestigationService orgBasedInvestigationService;

    public OrgBasedInvestigationController(OrgBasedInvestigationService orgBasedInvestigationService) {
        this.orgBasedInvestigationService = orgBasedInvestigationService;
    }

    @PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
    @PostMapping("/addOrgInvestigation")
    public ResponseEntity<?> addOrgInvestigation(@RequestBody OrgBasedInvestigationDTO orgBasedInvestigationDTO){
       OrgBasedInvestigationDTO orgBasedInvestigationDTO1 = OrgBasedInvestigationDTO.form(orgBasedInvestigationService.addOrgInvestigation(orgBasedInvestigationDTO));
       return new ResponseEntity<>(orgBasedInvestigationDTO1,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
    @PostMapping("/addAllOrgInvestigation")
    public ResponseEntity<?> addAllOrgInvestigation(@RequestBody List<OrgBasedInvestigationDTO> investigationDTOList){
        return new ResponseEntity<>(orgBasedInvestigationService.addMultipleOrgInvestigation(investigationDTOList),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN')")
    @GetMapping("/getAllOrgInvestigation/{orgId}")
    public ResponseEntity<?> getAllOrgInvestigation(@PathVariable Long orgId){
        List<OrgBasedInvestigation> orgBasedInvestigations = orgBasedInvestigationService.getAllOrgInvestigationById(orgId);
        List<OrgBasedInvestigationDTO> orgBasedInvestigationDTOList = orgBasedInvestigations.stream().map(OrgBasedInvestigationDTO::form).toList();
        return new ResponseEntity<>(orgBasedInvestigationDTOList,HttpStatus.OK);
    }

}
