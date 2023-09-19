package com.paul.billing_system.dto;

import com.paul.billing_system.entity.UserInfo;
import com.paul.billing_system.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    private String username;

    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    private String password;

    private String contact;

    private String roles;

    private String department;

    private String designation;

    public static UserInfoDTO form(UserInfo userInfo){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userInfo.getId());
        userInfoDTO.setName(userInfo.getName());
        userInfoDTO.setUsername(userInfo.getUsername());
        userInfoDTO.setEmail(userInfo.getEmail());
        userInfoDTO.setContact(userInfo.getContact());
        String userRoles =
                UserRoles.getLabelByUserRoles(userInfo.getRoles());
        userInfoDTO.setRoles(userRoles);
        userInfoDTO.setDepartment(userInfo.getDepartment());
        userInfoDTO.setDesignation(userInfo.getDesignation());
        return userInfoDTO;
    }
}
/*        String organizationTypes = OrganizationTypes.getLabelByOrganizationType(organization.getType());
        organizationDTO.setType(organizationTypes);*/
