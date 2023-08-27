package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Address Should Not Be Empty")
    private String address;
    private String contact;

    @Enumerated(EnumType.STRING)
    private String type;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
    private String website;

    public static OrganizationDTO form(Organization organization){
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setName(organization.getName());
        organizationDTO.setAddress(organization.getAddress());
        organizationDTO.setContact(organization.getContact());
        String organizationTypes = OrganizationTypes.getLabelByOrganizationType(organization.getType());
        organizationDTO.setType(organizationTypes);
        organizationDTO.setEmail(organization.getEmail());
        organizationDTO.setWebsite(organization.getWebsite());
        return organizationDTO;
    }
}
