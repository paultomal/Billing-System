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

    private List<OrgBasedInvestigationDTO> investigationDTOList;

    private Double total;

    public static InvestigationBookingDTO form(InvestigationBooking booking){
        InvestigationBookingDTO bookingDTO = new InvestigationBookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setOrg_id(booking.getOrganization().getId());
        bookingDTO.setPid(booking.getPatient().getId());
        bookingDTO.setP_name(booking.getPatient().getName());
        bookingDTO.setContact(booking.getPatient().getContact());

        if (booking.getOrgBasedInvestigationList() != null) {
            bookingDTO.setInvestigationDTOList(booking.getOrgBasedInvestigationList().stream().map(OrgBasedInvestigationDTO::form).toList());
        } else {
            bookingDTO.setInvestigationDTOList(null); // Handle the case where investigations is null
        }

        bookingDTO.setTotal(booking.getTotal());
        return bookingDTO;
    }
}
