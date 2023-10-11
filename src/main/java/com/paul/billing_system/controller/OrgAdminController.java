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
@RequestMapping("/org_admin")
@PreAuthorize("hasAuthority('ROLE_ROOT')")
public class OrgAdminController {
    private final UserServices userServices;

    public OrgAdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/addOrgAdmin")
    public ResponseEntity<?> save(@Valid @RequestBody UserInfoDTO userInfoDTO) throws EmailAlreadyTakenException, UserNameAlreadyTakenException {
        if (userServices.getUserByEmail(userInfoDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException(userInfoDTO.getEmail() + " is already taken!! Try Another!!");
        }

        if (userServices.getUserByUserName(userInfoDTO.getUsername()).isPresent()) {
            throw new UserNameAlreadyTakenException(userInfoDTO.getUsername() + " is already taken!! Try Another!!");
        }
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.saveOrgAdmin(userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }

    @GetMapping("/getOrgAdmins/{id}")
    public ResponseEntity<?> getOrgAdmins(@PathVariable Long id,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        List<UserInfo> userInfos = userServices.getOrgAdmins(id, PageRequest.of(page, size));
        List<UserInfoDTO> orgAdmin = userInfos.stream()
                .map(UserInfoDTO::form)
                .filter(u -> u.getRoles().equals(UserRoles.getLabelByUserRoles(UserRoles.ROLE_ORG_ADMIN)))
                .toList();
        return new ResponseEntity<>(orgAdmin, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        UserInfoDTO userInfoDTO = UserInfoDTO.form(userServices.getOrgAdminById(id));
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    @GetMapping("/getAdminLengthOfOrgAdmin/{orgId}")
    public ResponseEntity<?> getAdminLengthOfOrgAdmin(@PathVariable Long orgId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        long lengthOfAdmins = userServices.getOrgAdmins(orgId, PageRequest.of(page, size)).size();

        return new ResponseEntity<>(lengthOfAdmins, HttpStatus.OK);
    }

    @PutMapping("/updateOrgAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@Valid @PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO) throws UserIsNotFoundException {
        if (userServices.getOrgAdminById(id) == null) {
            throw new UserIsNotFoundException("Org Admin " + id + " is not Found.");
        }
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.updateOrgAdmin(id, userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }
}
