package com.paul.billing_system.entity;

import com.paul.billing_system.dto.AdminDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String roles;

    public static UserInfo form(AdminDTO adminDTO) {
    UserInfo userInfo=new UserInfo();
    userInfo.setName(adminDTO.getName());
    userInfo.setEmail(adminDTO.getEmail());
    userInfo.setPassword(adminDTO.getPassword());
    userInfo.setRoles("Admin");
    return userInfo;
    }
}
