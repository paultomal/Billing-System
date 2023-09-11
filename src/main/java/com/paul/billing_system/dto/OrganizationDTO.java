package com.paul.billing_system.dto;

import com.paul.billing_system.entity.MedSpecialist;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.enums.OrganizationTypes;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private String type;

    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    private String emergencyContact;

    private String operatingHour;

    private List<AdminDTO> admin;

    private List<DepartmentDTO> department;

    private List<PatientsDTO> patients;

    private List<CompoundersHospitalDTO> compoundersHospital;

    private List<CompoundersChamberDTO> compoundersChamber;

    private List<MedSpecialistDTO> medSpecialists;

    public static OrganizationDTO form(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setName(organization.getName());
        organizationDTO.setAddress(organization.getAddress());
        organizationDTO.setContact(organization.getContact());
        String organizationTypes = OrganizationTypes.getLabelByOrganizationType(organization.getType());
        organizationDTO.setType(organizationTypes);
        organizationDTO.setEmail(organization.getEmail());
        organizationDTO.setEmergencyContact(organization.getEmergencyContact());
        organizationDTO.setOperatingHour(organization.getOperatingHour());
        //organizationDTO.setDepartment(organization.getDepartments().stream().map(DepartmentDTO::form).toList());
        organizationDTO.setDepartment(organization.getDepartments()!=null ? organization.getDepartments().stream().map(DepartmentDTO::form).toList() : null);
        organizationDTO.setAdmin(organization.getAdmins()!=null ? organization.getAdmins().stream()
                .filter(adminDTO -> "Admin".equals(adminDTO.getRoles()))
                .map(AdminDTO::form).toList() : null);
        organizationDTO.setCompoundersHospital(organization.getCompounders()!= null ? organization.getCompounders().stream().map(CompoundersHospitalDTO::form).toList() : null);
        organizationDTO.setCompoundersChamber(organization.getCompounders()!=null ? organization.getCompounders().stream().map(CompoundersChamberDTO::form).toList() : null);
        organizationDTO.setPatients(organization.getPatients() != null ? organization.getPatients().stream().map(PatientsDTO::form).toList() : null);
        organizationDTO.setMedSpecialists(organization.getMedSpecialists() != null ? organization.getMedSpecialists().stream().map(MedSpecialistDTO::form).toList() : null);
        return organizationDTO;
    }
}
