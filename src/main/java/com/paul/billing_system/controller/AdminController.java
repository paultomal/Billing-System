package com.paul.billing_system.controller;

import com.paul.billing_system.dto.AdminDTO;
import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.service.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    private final UserServices userServices;

    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping ("/addAdmin/{id}")
    public ResponseEntity<?> save(@Valid @RequestBody AdminDTO adminDTO, BindingResult bindingResult,@PathVariable Long id){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation errors: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        AdminDTO adminDTO1 = AdminDTO.form(userServices.saveAdmin(id,adminDTO));
        return new ResponseEntity<>(adminDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAdmins")
    public ResponseEntity<?> getAdmins(){
        List<UserInfo> userInfos = userServices.getAdmins();
        List<UserInfo> admins = userInfos.stream()
                .filter(userInfo -> userInfo.getRoles().contains("Admin"))
                .toList();
        return new ResponseEntity<>(admins,HttpStatus.OK);
    }

    @GetMapping("/getAdmin/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id){
        AdminDTO adminDTO = AdminDTO.form(userServices.getAdminById(id));
        return new ResponseEntity<>(adminDTO,HttpStatus.OK);
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@Valid @PathVariable Long id, @RequestBody AdminDTO adminDTO,BindingResult bindingResult){
        AdminDTO adminDTO1 = AdminDTO.form(userServices.updateAdmin(id,adminDTO));
        return new ResponseEntity<>(adminDTO1,HttpStatus.OK);
    }
}
