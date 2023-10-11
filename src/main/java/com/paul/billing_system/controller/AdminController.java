package com.paul.billing_system.controller;

import com.paul.billing_system.dto.UserInfoDTO;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.enums.UserRoles;
import com.paul.billing_system.exception.EmailAlreadyTakenException;
import com.paul.billing_system.exception.UserIsNotFoundException;
import com.paul.billing_system.exception.UserNameAlreadyTakenException;
import com.paul.billing_system.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ROLE_ROOT','ROLE_ORG_ADMIN','ROLE_ADMIN')")

public class AdminController {
    private final UserServices userServices;

    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody UserInfoDTO userInfoDTO) throws EmailAlreadyTakenException, UserNameAlreadyTakenException {
        if(userServices.getUserByEmail(userInfoDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException(userInfoDTO.getEmail() + " is already registered!!! Try Another");
        }
        if (userServices.getUserByUserName(userInfoDTO.getUsername()).isPresent()) {
            throw new UserNameAlreadyTakenException(userInfoDTO.getUsername() + "is already registered!! Try Another");
        }
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.saveAdmin(userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAdmins/{org_Id}")
    public ResponseEntity<?> getAllAdmin(@PathVariable Long org_Id,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        List<UserInfo> userInfos = userServices.getAllAdmins(org_Id, PageRequest.of(page, size));
        List<UserInfoDTO> admin = userInfos.stream()
                .map(UserInfoDTO::form)
                .filter(u -> u.getRoles().equals(UserRoles.getLabelByUserRoles(UserRoles.ROLE_ADMIN)))
                .toList();
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        UserInfoDTO userInfoDTO = UserInfoDTO.form(userServices.getAdminById(id));
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody UserInfoDTO userInfoDTO, @PathVariable Long id) throws UserIsNotFoundException {
        if (userServices.getAdminById(id) == null){
            throw new UserIsNotFoundException("Org Admin "+ id + " is not Found.");
        }
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.updateAdmin(userInfoDTO, id));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }
}
