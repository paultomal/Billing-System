package com.paul.billing_system.controller;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.dto.OrgInvestigationDTO;
import com.paul.billing_system.entity.OrgInvestigationPrice;
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

    //@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
    @PostMapping("/addOrgInvestigation")
    public ResponseEntity<?> addOrgInvestigation(@RequestBody OrgInvestigationDTO orgInvestigationDTO){
       OrgInvestigationDTO orgInvestigationDTO1 = OrgInvestigationDTO.form(orgBasedInvestigationService.addOrgInvestigation(orgInvestigationDTO));
       return new ResponseEntity<>(orgInvestigationDTO1,HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
    @PostMapping("/addAllOrgInvestigation")
    public ResponseEntity<?> addAllOrgInvestigation(@RequestBody List<OrgInvestigationDTO> investigationDTOList){
        return new ResponseEntity<>(orgBasedInvestigationService.addMultipleOrgInvestigation(investigationDTOList),HttpStatus.OK);
    }


}


























/*    //@PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN')")
    @GetMapping("/getAllOrgInvestigation/{orgId}")
    public ResponseEntity<?> getAllOrgInvestigation(@PathVariable Long orgId){
        List<InvestigationDTO> globalInvestigationDTO = orgBasedInvestigationService.getAllOrgInvestigationById(orgId);
        List<OrgInvestigationDTO> orgInvestigationDTOList = globalInvestigationDTO.stream().map(InvestigationDTO::form).toList();
        return new ResponseEntity<>(orgInvestigationDTOList,HttpStatus.OK);
    }
    */

    /*    @GetMapping("/getAllDrugs/{orgId}")
    public List<DrugDTO> getDrugsByOrgId(@PathVariable Long orgId,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return drugService.getAllDrugsOfOrg(orgId, PageRequest.of(page,size));
    }
*/
