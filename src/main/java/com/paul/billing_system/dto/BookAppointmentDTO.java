package com.paul.billing_system.dto;

import com.paul.billing_system.entity.BookAppointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAppointmentDTO {
    private Long id;

    private Long pid;

    private String p_name;

    private String contact;

    private String doc_name;

    private String consultationFee;

    private String discount;

    public static BookAppointmentDTO form(BookAppointment bookAppointment){
        BookAppointmentDTO bookAppointmentDTO = new BookAppointmentDTO();
        bookAppointmentDTO.setId(bookAppointment.getId());
        bookAppointmentDTO.setPid(bookAppointment.getPatients().getId());
        bookAppointmentDTO.setP_name(bookAppointment.getPatients().getName());
        bookAppointmentDTO.setContact(bookAppointment.getPatients().getContact());
        bookAppointmentDTO.setDoc_name(bookAppointment.getDoctors().getName());
        bookAppointmentDTO.setConsultationFee(bookAppointment.getDoctors().getConsultationFee());
        bookAppointmentDTO.setDiscount(bookAppointment.getDoctors().getMaxDiscount());
        return bookAppointmentDTO;
    }

}
