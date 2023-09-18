package com.paul.billing_system.controller;

import com.paul.billing_system.dto.InvestigationDTO;
import com.paul.billing_system.entity.Investigation;
import com.paul.billing_system.exception.AuthenticationIsNotGivenException;
import com.paul.billing_system.service.InvestigationServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/investigation")
@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")
public class InvestigationController {
    private final InvestigationServices investigationServices;

    public InvestigationController(InvestigationServices investigationServices) {
        this.investigationServices = investigationServices;
    }

    @PostMapping("/addInvestigation/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody InvestigationDTO investigationDTO, BindingResult bindingResult, @PathVariable Long id){
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        InvestigationDTO investigationDTO1 = InvestigationDTO.form(investigationServices.save(id, investigationDTO));
        return new ResponseEntity<>(investigationDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllInvestigation")
    public ResponseEntity<?> getAllServices(@RequestHeader("Authorization") String token) throws AuthenticationIsNotGivenException {
   /*     List<ServicesInfo> servicesInfos = servicesInfoServices.getAllServices();
        List<ServicesInfoDTO> servicesInfoDTOList = servicesInfos.stream().map(ServicesInfoDTO::form).toList();
        return new ResponseEntity<>(servicesInfoDTOList,HttpStatus.OK);
*/
        if (token==null){
            throw new AuthenticationIsNotGivenException("No Token");
        }
            List<Investigation> investigations = investigationServices.getAllServices();
            List<InvestigationDTO> investigationDTOList = investigations.stream().map(InvestigationDTO::form).toList();
            return new ResponseEntity<>(investigationDTOList, HttpStatus.OK);
    }

    @GetMapping("/getInvestigation/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id){
        InvestigationDTO investigationDTO = InvestigationDTO.form(investigationServices.getServiceById(id));
        return new ResponseEntity<>(investigationDTO,HttpStatus.OK);
    }

    @PutMapping("/updateInvestigation/{id}")
    public ResponseEntity<?> updateService(@Valid @RequestBody InvestigationDTO investigationDTO, @PathVariable Long id, BindingResult bindingResult){
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        InvestigationDTO investigationDTO1 = InvestigationDTO.form(investigationServices.updateService(investigationDTO,id));
        return new ResponseEntity<>(investigationDTO1,HttpStatus.OK);
    }
}
