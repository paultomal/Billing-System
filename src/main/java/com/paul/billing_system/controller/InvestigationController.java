package com.paul.billing_system.controller;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.exception.InvestigationServiceNameAlreadyTaken;
import com.paul.billing_system.service.InvestigationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/investigation")
@PreAuthorize("hasAnyAuthority('ROLE_ORG_ADMIN','ROLE_ADMIN','ROLE_ROOT')")

public class InvestigationController {
    private final InvestigationService investigationService;

    public InvestigationController(InvestigationService investigationService) {
        this.investigationService = investigationService;
    }

    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @PostMapping("/addInvestigation")
    public ResponseEntity<?> save(@Valid @RequestBody InvestigationDTO investigationDTO) throws InvestigationServiceNameAlreadyTaken {
        if (investigationService.getInvestigationByServiceName(investigationDTO.getServiceName()).isPresent()){
            throw new InvestigationServiceNameAlreadyTaken(investigationDTO.getServiceName() + " is Already Saved in Database");
        }
        InvestigationDTO investigationDTO1 = InvestigationDTO.form(investigationService.saveInvestigation(investigationDTO));
        return new ResponseEntity<>(investigationDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllInvestigation")
    public ResponseEntity<?> getAllServices(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size){
        List<Investigation> investigations = investigationService.getAllServices(PageRequest.of(page, size));
        List<InvestigationDTO> investigationDTOList = investigations.stream().map(InvestigationDTO::form).toList();
        return new ResponseEntity<>(investigationDTOList, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ROOT')")
    @PutMapping("/updateInvestigation/{id}")
    public ResponseEntity<?> updateService(@Valid @RequestBody InvestigationDTO investigationDTO, @PathVariable Long id, BindingResult bindingResult) {

        InvestigationDTO investigationDTO1 = InvestigationDTO.form(investigationService.updateService(investigationDTO, id));
        return new ResponseEntity<>(investigationDTO1, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {

        return new ResponseEntity<>(investigationService.searchInvestigation(name, PageRequest.of(page, size))
                .stream()
                .map(InvestigationDTO::form)
                .toList(), HttpStatus.OK);

    }

    @GetMapping("/search/{orgId}/{name}")
    public List<InvestigationDTO> searchInvestigationBYOrg(@PathVariable Long orgId,
                                                           @PathVariable String name,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return investigationService.searchInvestigationBYOrg(orgId, name, PageRequest.of(page, size));
    }

    @GetMapping("/getAllInvestigationByOrg/{orgId}")
    public List<InvestigationDTO> getAllInvestigations(@PathVariable Long orgId,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return investigationService.getAllInvestigationByOrg(orgId, PageRequest.of(page, size));
    }


    @GetMapping("/getInvestigation/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id) {
        InvestigationDTO investigationDTO = InvestigationDTO.form(investigationService.getServiceById(id));
        return new ResponseEntity<>(investigationDTO, HttpStatus.OK);
    }

    @GetMapping("/getOrgBasedInvestigation/{orgId}/{id}")
    public InvestigationDTO getOrgBasedInvestigation(@PathVariable Long id,
                                                     @PathVariable Long orgId) {
        return investigationService.getOrgBasedInvestigation(id, orgId);
    }

    @GetMapping("/countInvestigation")
    public Long countInvestigation() {
        return investigationService.countInvestigation();
    }

}


