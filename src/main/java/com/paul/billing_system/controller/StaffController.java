package com.paul.billing_system.controller;

import com.paul.billing_system.dto.UserInfoDTO;
import com.paul.billing_system.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/staffs")
@PreAuthorize("hasAuthority('ROLE_Admin')")

public class StaffController {
    private final UserServices userServices;

    public StaffController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/addStaff/{id}")
    public ResponseEntity<?> saveStaff(@Valid @RequestBody UserInfoDTO userInfoDTO, BindingResult bindingResult, @PathVariable Long id) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.saveStaff(id, userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }

/*    @GetMapping("/{id}")
    public ResponseEntity<?> getCompounderById(@PathVariable Long id) {
        CompoundersDTO compoundersDTO = CompoundersDTO.form(userServices.getCompounderById(id));
        return new ResponseEntity<>(compoundersDTO, HttpStatus.OK);
    }


    @PutMapping("/updateCompounder/{id}")
    public ResponseEntity<?> updateCompounder(@Valid @RequestBody CompoundersDTO compoundersDTO, @PathVariable Long id, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        CompoundersDTO staffsDTO1 = CompoundersDTO.form(userServices.updateCompounder(compoundersDTO, id));
        return new ResponseEntity<>(staffsDTO1, HttpStatus.OK);
    }*/
}
