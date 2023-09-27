package com.paul.billing_system.dto;

import com.paul.billing_system.entity.InvestigationBooking;
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

    private List<InvestigationDTO> investigations;

    private Double total;

    public static InvestigationBookingDTO form(InvestigationBooking booking){
        InvestigationBookingDTO bookingDTO = new InvestigationBookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setOrg_id(booking.getOrganization().getId());
        bookingDTO.setPid(booking.getPatient().getId());
        bookingDTO.setP_name(booking.getPatient().getName());
        bookingDTO.setContact(booking.getPatient().getContact());

        if (booking.getInvestigations() != null) {
            bookingDTO.setInvestigations(booking.getInvestigations().stream().map(InvestigationDTO::form).toList());
        } else {
            bookingDTO.setInvestigations(null); // Handle the case where investigations is null
        }

        bookingDTO.setTotal(booking.getTotal());
        return bookingDTO;
    }
}
