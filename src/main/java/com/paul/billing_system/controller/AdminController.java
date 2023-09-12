package com.paul.billing_system.controller;

import com.paul.billing_system.dto.UserInfoDTO;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserServices userServices;

    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping ("/addAdmin/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody UserInfoDTO userInfoDTO, BindingResult bindingResult, @PathVariable Long id){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.saveAdmin(id, userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAdmins")
    public ResponseEntity<?> getAdmins(){
        List<UserInfo> userInfos = userServices.getAdmins();
        List<UserInfo> admins = userInfos.stream()
                .filter(userInfo -> userInfo.getRoles().contains("Admin"))
                .toList();
        return new ResponseEntity<>(admins,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id){
        UserInfoDTO userInfoDTO = UserInfoDTO.form(userServices.getAdminById(id));
        return new ResponseEntity<>(userInfoDTO,HttpStatus.OK);
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@Valid @PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO, BindingResult bindingResult){
        UserInfoDTO userInfoDTO1 = UserInfoDTO.form(userServices.updateAdmin(id, userInfoDTO));
        return new ResponseEntity<>(userInfoDTO1,HttpStatus.OK);
    }
}
