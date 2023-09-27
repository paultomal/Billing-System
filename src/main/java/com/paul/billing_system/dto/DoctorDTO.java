package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Doctors;
import com.paul.billing_system.entity.Organization;
import com.paul.billing_system.entity.Speciality;
import com.paul.billing_system.enums.DaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    private List<Long> orgId;

    private List<Long> spId;

    public static DoctorDTO form(Doctors doctors) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctors.getId());
        doctorDTO.setName(doctors.getName());
        doctorDTO.setDegrees(doctors.getDegrees());
        doctorDTO.setContact(doctors.getContact());
        doctorDTO.setEmail(doctors.getEmail());
        doctorDTO.setFollowUp(doctors.getFollowUp());
        doctorDTO.setConsultation(doctors.getConsultationFee());
        doctorDTO.setMinDiscount(doctors.getMinDiscount());
        doctorDTO.setMaxDiscount(doctors.getMaxDiscount());

        String days = DaysOfWeek.getLabelByDays(doctors.getDay());
        doctorDTO.setDay(days);

        doctorDTO.setTime(doctors.getTime());

        doctorDTO.setOrgId(doctors.getOrganization().stream().map(Organization::getId)
                .toList());
        doctorDTO.setSpId(doctors.getSpeciality().stream()
                .map(Speciality::getId)
                .toList());

        return doctorDTO;
    }
}
