package com.paul.billing_system.controller;

import com.paul.billing_system.dto.ServicesInfoDTO;
import com.paul.billing_system.entity.ServicesInfo;
import com.paul.billing_system.exception.AuthenticationIsNotGivenException;
import com.paul.billing_system.service.ServicesInfoServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/service")
@PreAuthorize("hasAuthority('ROLE_Admin')")
public class ServicesController {
    private final ServicesInfoServices servicesInfoServices;

    public ServicesController(ServicesInfoServices servicesInfoServices) {
        this.servicesInfoServices = servicesInfoServices;
    }

    @PostMapping("/addServices/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody ServicesInfoDTO servicesInfoDTO, BindingResult bindingResult,@PathVariable Long id){
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        ServicesInfoDTO servicesInfoDTO1 = ServicesInfoDTO.form(servicesInfoServices.save(id,servicesInfoDTO));
        return new ResponseEntity<>(servicesInfoDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllServices")
    public ResponseEntity<?> getAllServices(@RequestHeader("Authorization") String token) throws AuthenticationIsNotGivenException {
/*        List<ServicesInfo> servicesInfos = servicesInfoServices.getAllServices();
        List<ServicesInfoDTO> servicesInfoDTOList = servicesInfos.stream().map(ServicesInfoDTO::form).toList();
        return new ResponseEntity<>(servicesInfoDTOList,HttpStatus.OK);*/
        if (token==null){
            throw new AuthenticationIsNotGivenException("No Token");
        }
            List<ServicesInfo> servicesInfos = servicesInfoServices.getAllServices();
            List<ServicesInfoDTO> servicesInfoDTOList = servicesInfos.stream().map(ServicesInfoDTO::form).toList();
            return new ResponseEntity<>(servicesInfoDTOList, HttpStatus.OK);
    }

    @GetMapping("/getService/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id){
        ServicesInfoDTO servicesInfoDTO = ServicesInfoDTO.form(servicesInfoServices.getServiceById(id));
        return new ResponseEntity<>(servicesInfoDTO,HttpStatus.OK);
    }

    @PutMapping("/updateServices/{id}")
    public ResponseEntity<?> updateService( @Valid @RequestBody ServicesInfoDTO servicesInfoDTO, @PathVariable Long id, BindingResult bindingResult){
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        ServicesInfoDTO servicesInfoDTO1 = ServicesInfoDTO.form(servicesInfoServices.updateService(servicesInfoDTO,id));
        return new ResponseEntity<>(servicesInfoDTO1,HttpStatus.OK);
    }
}
