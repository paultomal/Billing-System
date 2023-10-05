package com.paul.billing_system.controller;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.exception.AuthenticationIsNotGivenException;
import com.paul.billing_system.service.InvestigationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/investigation")
public class InvestigationController {
    private final InvestigationService investigationService;

    public InvestigationController(InvestigationService investigationService) {
        this.investigationService = investigationService;
    }

    @PostMapping("/addInvestigation")
    public ResponseEntity<?> save(@Valid @RequestBody InvestigationDTO investigationDTO, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        InvestigationDTO investigationDTO1 = InvestigationDTO.form(investigationService.saveInvestigation(investigationDTO));
        return new ResponseEntity<>(investigationDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllInvestigation")
    public ResponseEntity<?> getAllServices(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestHeader("Authorization") String token) throws AuthenticationIsNotGivenException {
        if (token == null) {
            throw new AuthenticationIsNotGivenException("No Token");
        }

        List<Investigation> investigations = investigationService.getAllServices(PageRequest.of(page, size));
        List<InvestigationDTO> investigationDTOList = investigations.stream().map(InvestigationDTO::form).toList();
        return new ResponseEntity<>(investigationDTOList, HttpStatus.OK);
    }


    @PutMapping("/updateInvestigation/{id}")
    public ResponseEntity<?> updateService(@Valid @RequestBody InvestigationDTO investigationDTO, @PathVariable Long id, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
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
                                                           @RequestParam(defaultValue = "10") int size){
        return investigationService.searchInvestigationBYOrg(orgId,name,PageRequest.of(page,size));
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

}


