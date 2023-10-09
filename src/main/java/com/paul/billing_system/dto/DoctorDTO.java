package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Doctor;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private Long id;

    private String name;

    private String degrees;

    private String contact;

    private String email;

    private String followUp;

    private String consultation;

    private String minDiscount;

    private String maxDiscount;

    private List<DoctorSlotDTO> doctorSlotDTOList;

    private List<Long> orgId;

    private List<Long> spId;

    public static DoctorDTO form(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setDegrees(doctor.getDegrees());
        doctorDTO.setContact(doctor.getContact());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setFollowUp(doctor.getFollowUp());
        doctorDTO.setConsultation(doctor.getConsultationFee());
        doctorDTO.setMinDiscount(doctor.getMinDiscount());
        doctorDTO.setMaxDiscount(doctor.getMaxDiscount());

        doctorDTO.setDoctorSlotDTOList(doctor.getDoctorSlots().stream().map(DoctorSlotDTO::form).toList());

        doctorDTO.setOrgId(doctor.getOrganizationList().stream().map(Organization::getId)
                .toList());
        doctorDTO.setSpId(doctor.getSpecialityList().stream()
                .map(Speciality::getId)
                .toList());

        return doctorDTO;
    }
}
