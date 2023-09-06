package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Doctors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private Long id;

    private String doctorName;

    private String doctorDegree;

    public static DoctorDTO form(Doctors doctors){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctors.getId());
        doctorDTO.setDoctorName(doctors.getDoctorName());
        doctorDTO.setDoctorDegree(doctors.getDoctorDegree());
        return doctorDTO;
    }
}
