package com.paul.billing_system.dto;

import com.paul.billing_system.entity.InvestigationBooking;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestigationBookingDTO {
    private Long id;

    private Long org_id;

    private Long pid;

    private String p_name;

    private String contact;

    private List<InvestigationDTO> investigationDTOList;

    @NotNull
    private Double total;

    public static InvestigationBookingDTO form(InvestigationBooking booking){
        InvestigationBookingDTO bookingDTO = new InvestigationBookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setOrg_id(booking.getOrganization().getId());
        bookingDTO.setPid(booking.getPatient().getId());
        bookingDTO.setP_name(booking.getPatient().getName());
        bookingDTO.setContact(booking.getPatient().getContact());

        bookingDTO.setInvestigationDTOList(booking.getInvestigationList().stream().map(InvestigationDTO::form).toList());
        bookingDTO.setTotal(booking.getTotal());
        return bookingDTO;
    }
}
