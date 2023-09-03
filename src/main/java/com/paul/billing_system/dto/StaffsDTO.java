package com.paul.billing_system.dto;

import com.paul.billing_system.entity.Staffs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffsDTO {
    private String name;
    private String designation;

    public static StaffsDTO form(Staffs staffs){
        StaffsDTO staffsDTO = new StaffsDTO();
        staffsDTO.setName(staffs.getName());
        staffsDTO.setDesignation(staffs.getDesignation());
        return staffsDTO;
    }
}
