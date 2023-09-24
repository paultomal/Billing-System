package com.paul.billing_system.dto;

import com.paul.billing_system.entity.BookingInvestigation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingInvestigationDTO {
    private Long id;

    private String pname;

    private Long pid;

    private String contact;

    private List<InvestigationDTO> investigations;


    public static BookingInvestigationDTO form(BookingInvestigation booking){
        BookingInvestigationDTO bookingDTO = new BookingInvestigationDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setPname(booking.getPatients().getName());
        bookingDTO.setContact(booking.getPatients().getContact());

        if (booking.getInvestigations() != null) {
            bookingDTO.setInvestigations(booking.getInvestigations().stream().map(InvestigationDTO::form).toList());
        } else {
            bookingDTO.setInvestigations(null); // Handle the case where investigations is null
        }
        return bookingDTO;
    }
}
