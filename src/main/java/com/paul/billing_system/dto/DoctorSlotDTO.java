package com.paul.billing_system.dto;

import com.paul.billing_system.entity.DoctorSlot;
import com.paul.billing_system.enums.DaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSlotDTO {
    private Long id;

    private String day;

    private String time;

    public static DoctorSlotDTO form(DoctorSlot doctorSlot){
        DoctorSlotDTO doctorSlotDTO =new DoctorSlotDTO();
        doctorSlotDTO.setId(doctorSlot.getId());
        doctorSlotDTO.setDay(DaysOfWeek.getLabelByDays(doctorSlot.getDay()));
        doctorSlotDTO.setTime(doctorSlot.getTime());
        return doctorSlotDTO;
    }
}
