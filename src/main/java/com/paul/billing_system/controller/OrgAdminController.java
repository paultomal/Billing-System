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
@RequestMapping("/org_admin")
@PreAuthorize("hasAuthority('ROLE_ROOT')")
public class OrgAdminController {
    private final UserServices userServices;

    public OrgAdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/addOrgAdmin/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody UserInfoDTO userInfoDTO, BindingResult bindingResult, @PathVariable Long id) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.saveOrgAdmin(id, userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }

    @GetMapping("/getOrgAdmins/{id}")
    public ResponseEntity<?> getOrgAdmins(@PathVariable Long id) {
        List<UserInfo> userInfos = userServices.getOrgAdmins(id);
        List<UserInfoDTO> orgAdmin = userInfos.stream()
                .map(UserInfoDTO::form)
                .filter(u-> u.getRoles().equals(UserRoles.getLabelByUserRoles(UserRoles.ROLE_ORG_ADMIN)))
                .toList();
        return new ResponseEntity<>(orgAdmin, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        UserInfoDTO userInfoDTO = UserInfoDTO.form(userServices.getOrgAdminById(id));
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    @PutMapping("/updateOrgAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@Valid @PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO, BindingResult bindingResult) {
        ResponseEntity<?> errorDetails = getErrorDetails(bindingResult);
        if (errorDetails != null) return errorDetails;
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.updateOrgAdmin(id, userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }
}
