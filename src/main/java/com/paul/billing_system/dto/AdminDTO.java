package com.paul.billing_system.dto;

import com.paul.billing_system.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    public static AdminDTO form(UserInfo userInfo){
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setName(userInfo.getName());
        adminDTO.setEmail(userInfo.getEmail());
        adminDTO.setPassword(userInfo.getPassword());
        return adminDTO;
    }
}
