package com.paul.billing_system.controller;

import com.paul.billing_system.dto.UserInfoDTO;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.enums.UserRoles;
import com.paul.billing_system.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paul.billing_system.controller.AuthController.getErrorDetails;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ORG_ADMIN')")

public class AdminController {
    private final UserServices userServices;

    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/addAdmin/{id}")
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody UserInfoDTO userInfoDTO, BindingResult bindingResult, @PathVariable Long id) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.saveAdmin(id, userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAdmins/{id}")
    public ResponseEntity<?> getAllAdmin(@PathVariable Long id){
        List<UserInfo> userInfos = userServices.getAllAdmins(id);
        List<UserInfoDTO> admin = userInfos.stream()
                .map(UserInfoDTO::form)
                .filter(u-> u.getRoles().equals(UserRoles.getLabelByUserRoles(UserRoles.ROLE_ADMIN)))
                .toList();
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

   @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
       UserInfoDTO userInfoDTO = UserInfoDTO.form(userServices.getAdminById(id));
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody UserInfoDTO userInfoDTO, @PathVariable Long id, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.updateAdmin(userInfoDTO, id));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }
}