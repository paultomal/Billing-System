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

    private List<InvestigationDTO> investigations;

    private List<PatientsDTO> patients;

    public static BookingInvestigationDTO form(BookingInvestigation booking){
        BookingInvestigationDTO bookingDTO = new BookingInvestigationDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setInvestigations(booking.getInvestigations().stream().map(InvestigationDTO::form).toList());
        bookingDTO.setPatients(booking.getPatients().stream().map(PatientsDTO::form).toList());
        return bookingDTO;
    }
}
