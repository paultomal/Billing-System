package com.paul.billing_system.dto;

import com.paul.billing_system.entity.UserInfo;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Email(message = "Email should be valid")
    @Column(unique = true)
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
