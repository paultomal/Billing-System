package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.enums.DaysOfWeek;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String day;

    private String time;

    private Long orgId;

    private Long spId;

    public static DoctorDTO form(Doctors doctors){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctors.getId());
        doctorDTO.setName(doctors.getName());
        doctorDTO.setDegrees(doctors.getDegrees());
        doctorDTO.setContact(doctors.getContact());
        doctorDTO.setEmail(doctors.getEmail());
        doctorDTO.setFollowUp(doctors.getFollowUp());
        doctorDTO.setConsultation(doctors.getConsultation());
        doctorDTO.setMinDiscount(doctors.getMinDiscount());
        doctorDTO.setMaxDiscount(doctors.getMaxDiscount());

        String days = DaysOfWeek.getLabelByDays(doctors.getDay());
        doctorDTO.setDay(days);

        doctorDTO.setTime(doctors.getTime());

        doctorDTO.setOrgId(doctors.getOrganization().getId());
        doctorDTO.setSpId(doctors.getSpecialist().getId());

        return doctorDTO;
    }
}
